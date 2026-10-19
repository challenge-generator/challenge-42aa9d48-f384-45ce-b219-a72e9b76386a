package com.pragma.loanprocessing.application.service;


import com.pragma.loanprocessing.domain.port.CreditServiceException;
import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.domain.port.CreditServicePort;
import com.pragma.loanprocessing.domain.port.LoanRepositoryPort;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationRequest;
import com.pragma.loanprocessing.infrastructure.exception.LoanValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanValidationServiceTest {

    @Mock
    private LoanRepositoryPort loanRepositoryPort;

    @Mock
    private CreditServicePort creditServicePort;

    private LoanValidationService loanValidationService;

    @BeforeEach
    void setUp() {
        loanValidationService = new LoanValidationService(loanRepositoryPort, creditServicePort);
    }

    @Test
    void validateAndSave_ValidRequest_SavesSuccessfully() throws Exception {
        LoanApplicationRequest request = createValidRequest();
        LoanApplication savedLoan = createLoanApplication(UUID.randomUUID(), request);
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(false);
        when(creditServicePort.isEligibleForLoan(any(), anyString(), any(), anyString()))
                .thenReturn(true);
        when(creditServicePort.getCreditScore(any(), anyString())).thenReturn(750);
        when(loanRepositoryPort.save(any(LoanApplication.class))).thenReturn(savedLoan);

        LoanApplication result = loanValidationService.validateAndSave(request);

        assertNotNull(result);
        assertEquals(request.identificationNumber(), result.getIdentificationNumber());
        verify(loanRepositoryPort).save(any(LoanApplication.class));
        verify(creditServicePort).registerCreditQuery(any(), anyString(), anyString());
    }

    @Test
    void validateAndSave_DuplicateRequest_ThrowsException() {
        LoanApplicationRequest request = createValidRequest();
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(true);

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
        verify(loanRepositoryPort, never()).save(any(LoanApplication.class));
    }

    @Test
    void validateAndSave_IneligibleClient_ThrowsException() throws Exception {
        LoanApplicationRequest request = createValidRequest();
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(false);
        when(creditServicePort.isEligibleForLoan(any(), anyString(), any(), anyString()))
                .thenReturn(false);

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
        verify(loanRepositoryPort, never()).save(any(LoanApplication.class));
    }

    @Test
    void validateAndSave_InvalidAmount_ThrowsException() {
        LoanApplicationRequest request = new LoanApplicationRequest(
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("-1000"),
                12,
                "CEDULA"
        );

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
    }

    @Test
    void validateAndSave_InvalidTerm_ThrowsException() {
        LoanApplicationRequest request = new LoanApplicationRequest(
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("10000"),
                0,
                "CEDULA"
        );

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
    }

    @Test
    void validateAndSave_CreditServiceException_PropagatesException() throws Exception {
        LoanApplicationRequest request = createValidRequest();
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(false);
        when(creditServicePort.isEligibleForLoan(any(), anyString(), any(), anyString()))
                .thenThrow(new com.pragma.loanprocessing.domain.port.CreditServiceException("Service unavailable"));

        assertThrows(com.pragma.loanprocessing.domain.port.CreditServiceException.class, 
                () -> loanValidationService.validateAndSave(request));
    }

    @Test
    void validateAndSave_LowCreditScore_ThrowsException() throws Exception {
        LoanApplicationRequest request = createValidRequest();
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(false);
        when(creditServicePort.isEligibleForLoan(any(), anyString(), any(), anyString()))
                .thenReturn(true);
        when(creditServicePort.getCreditScore(any(), anyString())).thenReturn(400);

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
    }

    @Test
    void findById_ExistingId_ReturnsLoan() {
        UUID loanId = UUID.randomUUID();
        LoanApplication loan = createLoanApplication(loanId, createValidRequest());
        
        when(loanRepositoryPort.findById(loanId)).thenReturn(Optional.of(loan));

        Optional<LoanApplication> result = loanValidationService.findById(loanId);

        assertTrue(result.isPresent());
        assertEquals(loanId, result.get().getId());
    }

    @Test
    void findById_NonExistingId_ReturnsEmpty() {
        UUID loanId = UUID.randomUUID();
        
        when(loanRepositoryPort.findById(loanId)).thenReturn(Optional.empty());

        Optional<LoanApplication> result = loanValidationService.findById(loanId);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByClientId_ValidClient_ReturnsLoans() {
        UUID clientId = UUID.randomUUID();
        LoanApplication loan = createLoanApplication(UUID.randomUUID(), createValidRequest());
        
        when(loanRepositoryPort.findByClientId(clientId)).thenReturn(java.util.List.of(loan));

        java.util.List<LoanApplication> result = loanValidationService.findByClientId(clientId);

        assertEquals(1, result.size());
        assertEquals(clientId, result.get(0).getClientId());
    }

    private LoanApplicationRequest createValidRequest() {
        return new LoanApplicationRequest(
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("10000"),
                12,
                "CEDULA"
        );
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