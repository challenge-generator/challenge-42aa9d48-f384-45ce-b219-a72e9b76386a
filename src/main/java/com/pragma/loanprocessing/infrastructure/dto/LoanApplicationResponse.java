package com.pragma.loanprocessing.infrastructure.dto;

import com.pragma.loanprocessing.domain.model.LoanApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LoanApplicationResponse(
    UUID id,
    String clientName,
    String identificationNumber,
    String email,
    String phoneNumber,
    BigDecimal requestedAmount,
    String loanType,
    Integer termMonths,
    BigDecimal interestRate,
    String status,
    String rejectionReason,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    UUID clientId,
    BigDecimal monthlyPayment
) {
    public static LoanApplicationResponse fromEntity(LoanApplication entity) {
        if (entity == null) {
            return null;
        }
        return new LoanApplicationResponse(
            entity.getId(),
            entity.getClientName(),
            entity.getIdentificationNumber(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.getRequestedAmount(),
            entity.getLoanType(),
            entity.getTermMonths(),
            entity.getInterestRate(),
            entity.getStatus(),
            entity.getRejectionReason(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getClientId(),
            entity.calculateMonthlyPayment()
        );
    }

    public static LoanApplicationResponse fromEntityWithMessage(LoanApplication entity, String message) {
        if (entity == null) {
            return null;
        }
        return new LoanApplicationResponse(
            entity.getId(),
            entity.getClientName(),
            entity.getIdentificationNumber(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.getRequestedAmount(),
            entity.getLoanType(),
            entity.getTermMonths(),
            entity.getInterestRate(),
            entity.getStatus(),
            message,
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getClientId(),
            entity.calculateMonthlyPayment()
        );
    }
}