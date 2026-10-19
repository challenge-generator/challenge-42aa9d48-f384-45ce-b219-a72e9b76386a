package com.pragma.loanprocessing.infrastructure.controller;

import com.pragma.loanprocessing.application.service.LoanValidationService;
import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.domain.port.LoanRepositoryPort;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationRequest;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationResponse;
import com.pragma.loanprocessing.infrastructure.exception.LoanValidationException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanValidationService loanValidationService;
    private final LoanRepositoryPort loanRepositoryPort;

    @Autowired
    public LoanController(LoanValidationService loanValidationService,
                          LoanRepositoryPort loanRepositoryPort) {
        this.loanValidationService = loanValidationService;
        this.loanRepositoryPort = loanRepositoryPort;
    }

    @PostMapping
    @CircuitBreaker(name = "creditServiceCircuitBreaker", fallbackMethod = "createLoanFallback")
    @Retry(name = "creditServiceRetry")
    public ResponseEntity<LoanApplicationResponse> createLoanApplication(
            @Valid @RequestBody LoanApplicationRequest request) {
        loanValidationService.validateLoanApplication(request);

        boolean exists = loanRepositoryPort.existsByIdentificationNumberAndLoanType(
                request.identificationNumber(),
                request.loanType()
        );

        if (exists) {
            throw new LoanValidationException(
                    "A loan application with this identification number and loan type already exists",
                    "identificationNumber"
            );
        }

        LoanApplication loanApplication = mapToEntity(request);
        LoanApplication savedApplication = loanRepositoryPort.save(loanApplication);
        LoanApplicationResponse response = mapToResponse(savedApplication);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationResponse> getLoanApplicationById(@PathVariable UUID id) {
        LoanApplication loanApplication = loanRepositoryPort.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Loan application not found with id: " + id));

        LoanApplicationResponse response = mapToResponse(loanApplication);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<LoanApplicationResponse>> getLoanApplicationsByClientId(
            @PathVariable UUID clientId) {
        List<LoanApplication> applications = loanRepositoryPort.findByClientId(clientId);

        List<LoanApplicationResponse> responses = applications.stream()
                .map(this::mapToResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationResponse>> getAllLoanApplications() {
        throw new UnsupportedOperationException("Pagination not implemented yet - use client endpoint");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LoanApplicationResponse> updateLoanStatus(
            @PathVariable UUID id,
            @RequestParam String newStatus) {
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new LoanValidationException("Status cannot be empty", "newStatus");
        }

        LoanApplication updatedApplication = loanRepositoryPort.updateStatus(id, newStatus);
        LoanApplicationResponse response = mapToResponse(updatedApplication);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanApplication(@PathVariable UUID id) {
        loanRepositoryPort.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private LoanApplication mapToEntity(LoanApplicationRequest request) {
        return new LoanApplication(
                UUID.randomUUID(),
                request.clientId(),
                request.identificationNumber(),
                request.fullName(),
                request.email(),
                request.phoneNumber(),
                request.loanType(),
                request.requestedAmount(),
                request.termMonths(),
                request.purpose(),
                "PENDING",
                null,
                java.time.LocalDateTime.now(),
                null
        );
    }

    private LoanApplicationResponse mapToResponse(LoanApplication loanApplication) {
        return new LoanApplicationResponse(
                loanApplication.getId(),
                loanApplication.getClientId(),
                loanApplication.getIdentificationNumber(),
                loanApplication.getFullName(),
                loanApplication.getEmail(),
                loanApplication.getPhoneNumber(),
                loanApplication.getLoanType(),
                loanApplication.getRequestedAmount(),
                loanApplication.getTermMonths(),
                loanApplication.getPurpose(),
                loanApplication.getStatus(),
                loanApplication.getRejectionReason(),
                loanApplication.getCreatedAt(),
                loanApplication.getUpdatedAt()
        );
    }

    public ResponseEntity<LoanApplicationResponse> createLoanFallback(
            LoanApplicationRequest request, Throwable throwable) {
        LoanApplicationResponse fallbackResponse = new LoanApplicationResponse(
                null,
                request.clientId(),
                request.identificationNumber(),
                request.fullName(),
                request.email(),
                request.phoneNumber(),
                request.loanType(),
                request.requestedAmount(),
                request.termMonths(),
                request.purpose(),
                "FAILED",
                "Credit service temporarily unavailable",
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackResponse);
    }
}