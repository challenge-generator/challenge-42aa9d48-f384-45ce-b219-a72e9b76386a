package com.pragma.loanprocessing.application.service;

import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.domain.port.CreditServicePort;
import com.pragma.loanprocessing.domain.port.LoanRepositoryPort;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationRequest;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationResponse;
import com.pragma.loanprocessing.infrastructure.exception.LoanValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoanValidationService {

    private static final BigDecimal MIN_AMOUNT = new BigDecimal("1000.00");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("50000.00");
    private static final int MIN_TERM_MONTHS = 3;
    private static final int MAX_TERM_MONTHS = 48;
    private static final int MIN_CREDIT_SCORE = 650;

    private final LoanRepositoryPort loanRepository;
    private final CreditServicePort creditService;

    @Autowired
    public LoanValidationService(LoanRepositoryPort loanRepository, CreditServicePort creditService) {
        this.loanRepository = loanRepository;
        this.creditService = creditService;
    }

    public LoanApplicationResponse validateAndCreateLoanApplication(LoanApplicationRequest request) {
        validateRequest(request);

        LoanApplication loanApplication = buildLoanApplication(request);

        checkForDuplicateApplication(loanApplication);

        validateWithCreditService(loanApplication);

        LoanApplication savedApplication = loanRepository.save(loanApplication);

        return LoanApplicationResponse.fromEntity(savedApplication);
    }

    private void validateRequest(LoanApplicationRequest request) {
        if (request == null) {
            throw new LoanValidationException("La solicitud de préstamo no puede ser nula");
        }

        validateClientName(request.clientName());
        validateIdentificationNumber(request.identificationNumber());
        validateEmail(request.email());
        validatePhoneNumber(request.phoneNumber());
        validateRequestedAmount(request.requestedAmount());
        validateLoanType(request.loanType());
        validateTermMonths(request.termMonths());
    }

    private void validateClientName(String clientName) {
        if (clientName == null || clientName.isBlank()) {
            throw new LoanValidationException("El nombre del cliente es obligatorio");
        }
        if (clientName.length() < 3 || clientName.length() > 100) {
            throw new LoanValidationException("El nombre del cliente debe tener entre 3 y 100 caracteres");
        }
        if (!clientName.matches("^[a-zA-Z\\s\\u00C0-\\u00FF]+$")) {
            throw new LoanValidationException("El nombre del cliente solo puede contener letras y espacios");
        }
    }

    private void validateIdentificationNumber(String identificationNumber) {
        if (identificationNumber == null || identificationNumber.isBlank()) {
            throw new LoanValidationException("El número de identificación es obligatorio");
        }
        if (!identificationNumber.matches("^[0-9]{6,20}$")) {
            throw new LoanValidationException("El número de identificación debe tener entre 6 y 20 dígitos");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new LoanValidationException("El correo electrónico es obligatorio");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new LoanValidationException("El formato del correo electrónico no es válido");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new LoanValidationException("El número de teléfono es obligatorio");
        }
        String phoneRegex = "^\\+?[0-9]{10,15}$";
        if (!phoneNumber.replaceAll("\\s", "").matches(phoneRegex)) {
            throw new LoanValidationException("El formato del número de teléfono no es válido");
        }
    }

    private void validateRequestedAmount(BigDecimal requestedAmount) {
        if (requestedAmount == null) {
            throw new LoanValidationException("El monto solicitado es obligatorio");
        }
        if (requestedAmount.compareTo(MIN_AMOUNT) < 0) {
            throw new LoanValidationException("El monto mínimo solicitado es: " + MIN_AMOUNT);
        }
        if (requestedAmount.compareTo(MAX_AMOUNT) > 0) {
            throw new LoanValidationException("El monto máximo solicitado es: " + MAX_AMOUNT);
        }
    }

    private void validateLoanType(String loanType) {
        if (loanType == null || loanType.isBlank()) {
            throw new LoanValidationException("El tipo de préstamo es obligatorio");
        }
        String[] validTypes = {"PERSONAL", "CONSUMER", "MICRO", "EMPRESARIAL"};
        boolean isValid = false;
        for (String type : validTypes) {
            if (type.equalsIgnoreCase(loanType)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new LoanValidationException("Tipo de préstamo inválido. Tipos válidos: PERSONAL, CONSUMER, MICRO, EMPRESARIAL");
        }
    }

    private void validateTermMonths(Integer termMonths) {
        if (termMonths == null) {
            throw new LoanValidationException("El plazo en meses es obligatorio");
        }
        if (termMonths < MIN_TERM_MONTHS) {
            throw new LoanValidationException("El plazo mínimo es de " + MIN_TERM_MONTHS + " meses");
        }
        if (termMonths > MAX_TERM_MONTHS) {
            throw new LoanValidationException("El plazo máximo es de " + MAX_TERM_MONTHS + " meses");
        }
    }

    private LoanApplication buildLoanApplication(LoanApplicationRequest request) {
        UUID clientId = UUID.randomUUID();
        BigDecimal interestRate = determineInterestRate(request.loanType(), request.requestedAmount());

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setId(UUID.randomUUID());
        loanApplication.setClientName(request.clientName());
        loanApplication.setIdentificationNumber(request.identificationNumber());
        loanApplication.setEmail(request.email());
        loanApplication.setPhoneNumber(request.phoneNumber());
        loanApplication.setRequestedAmount(request.requestedAmount());
        loanApplication.setLoanType(request.loanType().toUpperCase());
        loanApplication.setTermMonths(request.termMonths());
        loanApplication.setInterestRate(interestRate);
        loanApplication.setStatus("PENDING");
        loanApplication.setClientId(clientId);
        loanApplication.setCreatedAt(LocalDateTime.now());
        loanApplication.setUpdatedAt(LocalDateTime.now());

        return loanApplication;
    }

    private BigDecimal determineInterestRate(String loanType, BigDecimal amount) {
        return switch (loanType.toUpperCase()) {
            case "PERSONAL" -> new BigDecimal("0.025");
            case "CONSUMER" -> new BigDecimal("0.035");
            case "MICRO" -> new BigDecimal("0.040");
            case "EMPRESARIAL" -> amount.compareTo(new BigDecimal("10000")) > 0
                    ? new BigDecimal("0.055")
                    : new BigDecimal("0.045");
            default -> new BigDecimal("0.030");
        };
    }

    private void checkForDuplicateApplication(LoanApplication loanApplication) {
        boolean exists = loanRepository.existsByIdentificationNumberAndLoanType(
            loanApplication.getIdentificationNumber(),
            loanApplication.getLoanType()
        );

        if (exists) {
            throw new LoanValidationException(
                "Ya existe una solicitud de préstamo activa para el cliente con identificación: "
                + loanApplication.getIdentificationNumber() + " y tipo: " + loanApplication.getLoanType()
            );
        }
    }

    private void validateWithCreditService(LoanApplication loanApplication) {
        try {
            int creditScore = creditService.getCreditScore(
                loanApplication.getClientId(),
                loanApplication.getIdentificationNumber()
            );

            if (creditScore < MIN_CREDIT_SCORE) {
                loanApplication.reject("Puntuación crediticia insuficiente: " + creditScore);
                loanRepository.save(loanApplication);
                throw new LoanValidationException(
                    "Su puntuación crediticia (" + creditScore + ") no cumple el requisito mínimo (" 
                    + MIN_CREDIT_SCORE + "). Solicitud rechazada."
                );
            }

            boolean isEligible = creditService.isEligibleForLoan(
                loanApplication.getClientId(),
                loanApplication.getIdentificationNumber(),
                loanApplication.getRequestedAmount(),
                loanApplication.getLoanType()
            );

            if (!isEligible) {
                loanApplication.reject("No elegible según el servicio de crédito");
                loanRepository.save(loanApplication);
                throw new LoanValidationException("El cliente no es elegible para el préstamo solicitado");
            }

            creditService.registerCreditQuery(
                loanApplication.getClientId(),
                loanApplication.getIdentificationNumber(),
                loanApplication.getLoanType()
            );

        } catch (Exception e) {
            loanApplication.reject("Error en validación de crédito: " + e.getMessage());
            loanRepository.save(loanApplication);
            throw new LoanValidationException("Error al validar con el servicio de crédito: " + e.getMessage());
        }
    }

    public LoanApplicationResponse findById(UUID id) {
        return loanRepository.findById(id)
            .map(LoanApplicationResponse::fromEntity)
            .orElseThrow(() -> new LoanValidationException("Solicitud de préstamo no encontrada: " + id));
    }

    public LoanApplicationResponse findByClientId(UUID clientId) {
        return loanRepository.findByClientId(clientId).stream()
            .findFirst()
            .map(LoanApplicationResponse::fromEntity)
            .orElseThrow(() -> new LoanValidationException(
                "No se encontró solicitud de préstamo para el cliente: " + clientId));
    }
}