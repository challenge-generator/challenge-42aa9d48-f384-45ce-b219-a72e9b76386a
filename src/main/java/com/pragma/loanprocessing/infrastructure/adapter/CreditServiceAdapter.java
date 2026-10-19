package com.pragma.loanprocessing.infrastructure.adapter;

import com.pragma.loanprocessing.domain.port.CreditServicePort;
import com.pragma.loanprocessing.domain.port.CreditServiceException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class CreditServiceAdapter implements CreditServicePort {

    private static final Logger log = LoggerFactory.getLogger(CreditServiceAdapter.class);
    private static final int DEFAULT_CIRCUIT_BREAKER_THRESHOLD = 5;
    private static final double DEFAULT_FAILURE_RATE_THRESHOLD = 50.0;

    private final RestTemplate restTemplate;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Value("${credit.service.base-url:http://localhost:8081}")
    private String creditServiceBaseUrl;

    @Value("${credit.service.timeout:3000}")
    private int serviceTimeout;

    public CreditServiceAdapter(RestTemplate restTemplate, CircuitBreakerRegistry circuitBreakerRegistry) {
        this.restTemplate = restTemplate;
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @Override
    public int getCreditScore(UUID clientId, String identificationNumber) throws CreditServiceException {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("creditService");
        Supplier<Integer> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
            return executeCreditScoreRequest(clientId, identificationNumber);
        });

        try {
            return decoratedSupplier.get();
        } catch (Exception e) {
            log.error("Error retrieving credit score for client: {}", clientId, e);
            throw new CreditServiceException("Failed to retrieve credit score", e);
        }
    }

    @Override
    public boolean isEligibleForLoan(UUID clientId, String identificationNumber, 
                                     BigDecimal requestedAmount, String loanType) throws CreditServiceException {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("creditServiceEligibility");
        Supplier<Boolean> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
            return executeEligibilityCheck(clientId, identificationNumber, requestedAmount, loanType);
        });

        try {
            return decoratedSupplier.get();
        } catch (Exception e) {
            log.error("Error checking eligibility for client: {}", clientId, e);
            throw new CreditServiceException("Failed to check loan eligibility", e);
        }
    }

    @Override
    public void registerCreditQuery(UUID clientId, String identificationNumber, String loanType) 
            throws CreditServiceException {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("creditServiceRegister");
        Runnable decoratedRunnable = CircuitBreaker.decorateRunnable(circuitBreaker, () -> {
            executeRegistrationRequest(clientId, identificationNumber, loanType);
        });

        try {
            decoratedRunnable.run();
        } catch (Exception e) {
            log.error("Error registering credit query for client: {}", clientId, e);
            throw new CreditServiceException("Failed to register credit query", e);
        }
    }

    private int executeCreditScoreRequest(UUID clientId, String identificationNumber) {
        String url = String.format("%s/api/credit/score?clientId=%s&identification=%s", 
                creditServiceBaseUrl, clientId.toString(), identificationNumber);

        try {
            CreditScoreResponse response = restTemplate.getForObject(url, CreditScoreResponse.class);
            return response != null ? response.score() : 0;
        } catch (Exception e) {
            log.warn("Credit service unavailable, using fallback score: 0");
            return 0;
        }
    }

    private boolean executeEligibilityCheck(UUID clientId, String identificationNumber,
                                            BigDecimal requestedAmount, String loanType) {
        String url = String.format("%s/api/credit/eligibility?clientId=%s&identification=%s&amount=%s&loanType=%s",
                creditServiceBaseUrl, clientId.toString(), identificationNumber, 
                requestedAmount.toString(), loanType);

        try {
            EligibilityResponse response = restTemplate.getForObject(url, EligibilityResponse.class);
            return response != null && response.eligible();
        } catch (Exception e) {
            log.warn("Credit service unavailable, defaulting eligibility to false");
            return false;
        }
    }

    private void executeRegistrationRequest(UUID clientId, String identificationNumber, String loanType) {
        String url = String.format("%s/api/credit/query", creditServiceBaseUrl);
        CreditQueryRequest request = new CreditQueryRequest(clientId, identificationNumber, loanType);

        try {
            restTemplate.postForObject(url, request, Void.class);
            log.info("Credit query registered successfully for client: {}", clientId);
        } catch (Exception e) {
            log.warn("Failed to register credit query: {}", e.getMessage());
        }
    }

    public static CircuitBreakerConfig createDefaultConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(DEFAULT_FAILURE_RATE_THRESHOLD)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(DEFAULT_CIRCUIT_BREAKER_THRESHOLD)
                .minimumNumberOfCalls(3)
                .permittedNumberOfCallsInHalfOpenState(2)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
    }

    record CreditScoreResponse(int score) {}
    record EligibilityResponse(boolean eligible) {}
    record CreditQueryRequest(UUID clientId, String identificationNumber, String loanType) {}
}