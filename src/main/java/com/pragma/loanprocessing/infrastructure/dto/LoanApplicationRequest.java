package com.pragma.loanprocessing.infrastructure.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO para recibir datos de solicitudes de préstamos desde el cliente.
 * Contiene validaciones de formato y reglas de negocio básicas.
 */
public record LoanApplicationRequest(

    @NotNull(message = "El ID del cliente es obligatorio")
    UUID clientId,

    @NotBlank(message = "El número de identificación es obligatorio")
    @Size(min = 5, max = 20, message = "El número de identificación debe tener entre 5 y 20 caracteres")
    String identificationNumber,

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 100, message = "El nombre completo no puede exceder 100 caracteres")
    String fullName,

    @NotNull(message = "El monto solicitado es obligatorio")
    @Positive(message = "El monto solicitado debe ser positivo")
    @DecimalMax(value = "1000000.00", message = "El monto solicitado no puede exceder 1,000,000")
    BigDecimal requestedAmount,

    @NotBlank(message = "El tipo de préstamo es obligatorio")
    @Pattern(regexp = "^(PERSONAL|VEHICLE|HOME|EDUCATION)$", message = "Tipo de préstamo inválido")
    String loanType,

    @NotNull(message = "El plazo en meses es obligatorio")
    @Min(value = 1, message = "El plazo debe ser al menos 1 mes")
    @Max(value = 240, message = "El plazo no puede exceder 240 meses")
    Integer termMonths,

    @NotBlank(message = "El propósito del préstamo es obligatorio")
    @Size(max = 200, message = "El propósito no puede exceder 200 caracteres")
    String purpose,

    @Email(message = "El correo electrónico debe ser válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    String email,

    @Pattern(regexp = "^\+?[0-9]{10,15}$", message = "El número de teléfono debe ser válido")
    @NotBlank(message = "El número de teléfono es obligatorio")
    String phoneNumber,

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    String address
) {
    /**
     * Constructor compacto para facilitar la creación de instancias en tests.
     */
    public LoanApplicationRequest {
        // Validación adicional personalizada
        if (requestedAmount != null && termMonths != null && requestedAmount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal maxAmount = BigDecimal.valueOf(1000000);
            if (requestedAmount.compareTo(maxAmount) > 0) {
                throw new IllegalArgumentException("El monto solicitado no puede exceder 1,000,000");
            }
        }
    }
}