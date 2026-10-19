package com.pragma.loanprocessing.domain.port;

import com.pragma.loanprocessing.domain.model.LoanApplication;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto que define las operaciones de persistencia para el dominio de préstamos.
 * Esta interfaz es implementada por la capa de infraestructura para interactuar
 * con el repositorio de datos.
 */
public interface LoanRepositoryPort {

    /**
     * Guarda una solicitud de préstamo en el repositorio.
     *
     * @param loanApplication la solicitud de préstamo a guardar
     * @return la solicitud de préstamo guardada con su ID generado
     */
    LoanApplication save(LoanApplication loanApplication);

    /**
     * Busca una solicitud de préstamo por su ID.
     *
     * @param id el ID de la solicitud de préstamo
     * @return un Optional que contiene la solicitud si existe, o vacío si no
     */
    Optional<LoanApplication> findById(UUID id);

    /**
     * Busca todas las solicitudes de préstamo asociadas a un cliente.
     *
     * @param clientId el ID del cliente
     * @return lista de solicitudes de préstamo del cliente
     */
    List<LoanApplication> findByClientId(UUID clientId);

    /**
     * Verifica si existe una solicitud de préstamo con el mismo número de identificación
     * y tipo de préstamo para evitar duplicados.
     *
     * @param identificationNumber número de identificación del cliente
     * @param loanType tipo de préstamo
     * @return true si existe una solicitud duplicada, false en caso contrario
     */
    boolean existsByIdentificationNumberAndLoanType(String identificationNumber, String loanType);

    /**
     * Actualiza el estado de una solicitud de préstamo.
     *
     * @param id el ID de la solicitud de préstamo
     * @param newStatus el nuevo estado de la solicitud
     * @return la solicitud de préstamo actualizada
     */
    LoanApplication updateStatus(UUID id, String newStatus);

    /**
     * Elimina una solicitud de préstamo por su ID.
     *
     * @param id el ID de la solicitud de préstamo a eliminar
     */
    void deleteById(UUID id);
}