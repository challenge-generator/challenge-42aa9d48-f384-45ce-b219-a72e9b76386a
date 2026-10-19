package com.pragma.loanprocessing.infrastructure.adapter;

import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.domain.port.LoanRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LoanJpaRepositoryAdapter implements LoanRepositoryPort {

    private final LoanApplicationRepository repository;

    public LoanJpaRepositoryAdapter(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        return repository.save(loanApplication);
    }

    @Override
    public Optional<LoanApplication> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<LoanApplication> findByClientId(UUID clientId) {
        return repository.findByClientId(clientId);
    }

    @Override
    public boolean existsByIdentificationNumberAndLoanType(String identificationNumber, String loanType) {
        return repository.existsByIdentificationNumberAndLoanType(identificationNumber, loanType);
    }

    @Override
    public LoanApplication updateStatus(UUID id, String newStatus) {
        Optional<LoanApplication> existingLoan = repository.findById(id);
        if (existingLoan.isPresent()) {
            LoanApplication loan = existingLoan.get();
            loan.setStatus(newStatus);
            return repository.save(loan);
        }
        throw new IllegalArgumentException("Loan application not found with id: " + id);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Repository
    public interface LoanApplicationRepository extends JpaRepository<LoanApplication, UUID> {

        List<LoanApplication> findByClientId(UUID clientId);

        boolean existsByIdentificationNumberAndLoanType(String identificationNumber, String loanType);

        @Modifying
        @Query("UPDATE LoanApplication l SET l.status = :status WHERE l.id = :id")
        int updateStatusById(@Param("id") UUID id, @Param("status") String status);
    }
}