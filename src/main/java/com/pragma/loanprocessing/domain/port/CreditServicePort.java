package com.pragma.loanprocessing.domain.port;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto que define las operaciones para interactuar con el servicio de crédito externo.
 * Esta interfaz es implementada por la capa de infraestructura para realizar consultas
 * de crédito y validar la elegibilidad de los clientes.
 */
public interface CreditServicePort {

    /**
     * Consulta el historial crediticio de un cliente en el servicio externo.
     *
     * @param clientId el ID del cliente
     * @param identificationNumber número de identificación del cliente
     * @return puntaje de crédito del cliente (0-1000)
     * @throws CreditServiceException si ocurre un error al consultar el servicio externo
     */
    int getCreditScore(UUID clientId, String identificationNumber) throws CreditServiceException;

    /**
     * Verifica si un cliente es elegible para un préstamo basado en su historial crediticio.
     *
     * @param clientId el ID del cliente
     * @param identificationNumber número de identificación del cliente
     * @param requestedAmount monto solicitado para el préstamo
     * @param loanType tipo de préstamo solicitado
     * @return true si el cliente es elegible, false en caso contrario
     * @throws CreditServiceException si ocurre un error al verificar la elegibilidad
     */
    boolean isEligibleForLoan(UUID clientId, String identificationNumber, BigDecimal requestedAmount, String loanType)
            throws CreditServiceException;

    /**
     * Registra una consulta de crédito en el servicio externo para auditoría.
     *
     * @param clientId el ID del cliente
     * @param identificationNumber número de identificación del cliente
     * @param loanType tipo de préstamo consultado
     * @throws CreditServiceException si ocurre un error al registrar la consulta
     */
    void registerCreditQuery(UUID clientId, String identificationNumber, String loanType) throws CreditServiceException;
}

/**
 * Excepción lanzada cuando ocurre un error en la interacción con el servicio de crédito externo.
 */
class CreditServiceException extends Exception {
    public CreditServiceException(String message) {
        super(message);
    }

    public CreditServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}