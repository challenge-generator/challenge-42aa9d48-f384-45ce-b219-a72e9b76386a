package com.pragma.loanprocessing.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.loanprocessing.application.service.LoanValidationService;
import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationRequest;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationResponse;
import com.pragma.loanprocessing.infrastructure.exception.GlobalExceptionHandler;
import com.pragma.loanprocessing.infrastructure.exception.LoanValidationException;
import com.pragma.loanprocessing.domain.port.CreditServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
@Import(GlobalExceptionHandler.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanValidationService loanValidationService;

    private LoanApplicationRequest validRequest;
    private LoanApplicationResponse validResponse;
    private UUID existingLoanId;

    @BeforeEach
    void setUp() {
        validRequest = new LoanApplicationRequest(
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("10000"),
                12,
                "CEDULA"
        );

        existingLoanId = UUID.randomUUID();
        validResponse = new LoanApplicationResponse(
                existingLoanId,
                UUID.randomUUID(),
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("10000"),
                12,
                "CEDULA",
                "PENDING",
                LocalDate.now()
        );
    }

    @Test
    void createLoan_ValidRequest_ReturnsCreated() throws Exception {
        when(loanValidationService.validateAndSave(any(LoanApplicationRequest.class)))
                .thenReturn(createLoanApplication(existingLoanId, validRequest));

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(existingLoanId.toString()))
                .andExpect(jsonPath("$.identificationNumber").value("12345678"));
    }

    @Test
    void createLoan_InvalidRequest_ReturnsBadRequest() throws Exception {
        LoanApplicationRequest invalidRequest = new LoanApplicationRequest(
                "",
                "",
                "invalid-email",
                "PERSONAL",
                new BigDecimal("-1000"),
                -5,
                ""
        );

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createLoan_DuplicateRequest_ReturnsConflict() throws Exception {
        when(loanValidationService.validateAndSave(any(LoanApplicationRequest.class)))
                .thenThrow(new LoanValidationException("Duplicate loan application"));

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isConflict());
    }

    @Test
    void createLoan_CreditServiceError_ReturnsServiceUnavailable() throws Exception {
        when(loanValidationService.validateAndSave(any(LoanApplicationRequest.class)))
                .thenThrow(new CreditServiceException("Credit service unavailable"));

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void getLoan_ExistingId_ReturnsOk() throws Exception {
        when(loanValidationService.findById(existingLoanId))
                .thenReturn(Optional.of(createLoanApplication(existingLoanId, validRequest)));

        mockMvc.perform(get("/api/v1/loans/{id}", existingLoanId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingLoanId.toString()));
    }

    @Test
    void getLoan_NonExistingId_ReturnsNotFound() throws Exception {
        UUID nonExistingId = UUID.randomUUID();
        when(loanValidationService.findById(nonExistingId))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/loans/{id}", nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void getLoansByClient_ValidClientId_ReturnsOk() throws Exception {
        UUID clientId = UUID.randomUUID();
        when(loanValidationService.findByClientId(clientId))
                .thenReturn(List.of(createLoanApplication(existingLoanId, validRequest)));

        mockMvc.perform(get("/api/v1/loans/client/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getLoansByClient_ClientWithNoLoans_ReturnsEmptyList() throws Exception {
        UUID clientId = UUID.randomUUID();
        when(loanValidationService.findByClientId(clientId))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/loans/client/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void deleteLoan_ExistingId_ReturnsNoContent() throws Exception {
        doNothing().when(loanValidationService).deleteLoan(existingLoanId);

        mockMvc.perform(delete("/api/v1/loans/{id}", existingLoanId))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteLoan_NonExistingId_ReturnsNotFound() throws Exception {
        UUID nonExistingId = UUID.randomUUID();
        doThrow(new LoanValidationException("Loan not found"))
                .when(loanValidationService).deleteLoan(nonExistingId);

        mockMvc.perform(delete("/api/v1/loans/{id}", nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void createLoan_MissingRequiredFields_ReturnsBadRequest() throws Exception {
        LoanApplicationRequest incompleteRequest = new LoanApplicationRequest(
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incompleteRequest)))
                .andExpect(status().isBadRequest());
    }

    private LoanApplication createLoanApplication(UUID id, LoanApplicationRequest request) {
        return LoanApplication.builder()
                .id(id)
                .clientId(UUID.randomUUID())
                .identificationNumber(request.identificationNumber())
                .clientName(request.clientName())
                .clientEmail(request.clientEmail())
                .loanType(request.loanType())
                .requestedAmount(request.requestedAmount())
                .termMonths(request.termMonths())
                .identificationType(request.identificationType())
                .status("PENDING")
                .applicationDate(LocalDate.now())
                .build();
    }
}