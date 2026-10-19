package com.pragma.loanprocessing.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class LoanApplication {
    private UUID id;
    private String clientName;
    private String identificationNumber;
    private String email;
    private String phoneNumber;
    private BigDecimal requestedAmount;
    private String loanType;
    private Integer termMonths;
    private BigDecimal interestRate;
    private String status;
    private String rejectionReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID clientId;

    public LoanApplication() {
    }

    public LoanApplication(UUID id, String clientName, String identificationNumber, String email,
                          String phoneNumber, BigDecimal requestedAmount, String loanType,
                          Integer termMonths, BigDecimal interestRate, String status,
                          String rejectionReason, LocalDateTime createdAt, LocalDateTime updatedAt,
                          UUID clientId) {
        this.id = id;
        this.clientName = clientName;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.requestedAmount = requestedAmount;
        this.loanType = loanType;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.clientId = clientId;
    }

    public void approve() {
        if (!canBeApproved()) {
            throw new IllegalStateException("La solicitud no cumple los requisitos para ser aprobada");
        }
        this.status = "APPROVED";
        this.updatedAt = LocalDateTime.now();
    }

    public void reject(String reason) {
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("El motivo de rechazo es obligatorio");
        }
        this.status = "REJECTED";
        this.rejectionReason = reason;
        this.updatedAt = LocalDateTime.now();
    }

    public void pending() {
        this.status = "PENDING";
        this.updatedAt = LocalDateTime.now();
    }

    private boolean canBeApproved() {
        return "PENDING".equals(this.status) &&
               this.requestedAmount != null &&
               this.requestedAmount.compareTo(BigDecimal.ZERO) > 0 &&
               this.termMonths != null &&
               this.termMonths > 0;
    }

    public boolean isDuplicateOf(LoanApplication other) {
        if (other == null) return false;
        return this.identificationNumber.equals(other.identificationNumber) &&
               this.loanType.equals(other.loanType);
    }

    public boolean hasValidAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        if (this.requestedAmount == null) return false;
        return this.requestedAmount.compareTo(minAmount) >= 0 &&
               this.requestedAmount.compareTo(maxAmount) <= 0;
    }

    public boolean hasValidTermRange(Integer minMonths, Integer maxMonths) {
        if (this.termMonths == null) return false;
        return this.termMonths >= minMonths && this.termMonths <= maxMonths;
    }

    public BigDecimal calculateMonthlyPayment() {
        if (this.requestedAmount == null || this.termMonths == null || this.interestRate == null) {
            return BigDecimal.ZERO;
        }
        if (this.termMonths == 0) return this.requestedAmount;
        BigDecimal monthlyRate = this.interestRate.divide(BigDecimal.valueOf(12), 10, java.math.RoundingMode.HALF_UP);
        BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(this.termMonths);
        return this.requestedAmount.multiply(monthlyRate).multiply(factor)
                .divide(factor.subtract(BigDecimal.ONE), 2, java.math.RoundingMode.HALF_UP);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}