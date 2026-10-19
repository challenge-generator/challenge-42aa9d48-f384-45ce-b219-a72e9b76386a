package com.pragma.loanprocessing.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties(prefix = "loan-processing")
public class LoanConfig {

    private static final int DEFAULT_CIRCUIT_BREAKER_THRESHOLD = 5;
    private static final double DEFAULT_FAILURE_RATE_THRESHOLD = 50.0;
    private static final Duration DEFAULT_WAIT_DURATION = Duration.ofSeconds(30);
    private static final Duration DEFAULT_TIMEOUT_DURATION = Duration.ofMillis(200);

    private int maxLoanAmount = 100000;
    private int minLoanAmount = 1000;
    private int maxTermMonths = 60;
    private int minTermMonths = 1;
    private double interestRate = 0.15;

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(DEFAULT_FAILURE_RATE_THRESHOLD)
                .waitDurationInOpenState(DEFAULT_WAIT_DURATION)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(DEFAULT_CIRCUIT_BREAKER_THRESHOLD)
                .minimumNumberOfCalls(3)
                .permittedNumberOfCallsInHalfOpenState(2)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();

        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public TimeLimiterRegistry timeLimiterRegistry() {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(DEFAULT_TIMEOUT_DURATION)
                .cancelRunningFuture(true)
                .build();

        return TimeLimiterRegistry.of(config);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public LoanProcessingProperties loanProcessingProperties() {
        return new LoanProcessingProperties(
                maxLoanAmount,
                minLoanAmount,
                maxTermMonths,
                minTermMonths,
                interestRate
        );
    }

    public int getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(int maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public int getMinLoanAmount() {
        return minLoanAmount;
    }

    public void setMinLoanAmount(int minLoanAmount) {
        this.minLoanAmount = minLoanAmount;
    }

    public int getMaxTermMonths() {
        return maxTermMonths;
    }

    public void setMaxTermMonths(int maxTermMonths) {
        this.maxTermMonths = maxTermMonths;
    }

    public int getMinTermMonths() {
        return minTermMonths;
    }

    public void setMinTermMonths(int minTermMonths) {
        this.minTermMonths = minTermMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public record LoanProcessingProperties(
            int maxLoanAmount,
            int minLoanAmount,
            int maxTermMonths,
            int minTermMonths,
            double interestRate
    ) {}

    public boolean isValidLoanAmount(int amount) {
        return amount >= minLoanAmount && amount <= maxLoanAmount;
    }

    public boolean isValidTerm(int months) {
        return months >= minTermMonths && months <= maxTermMonths;
    }

    public double calculateMonthlyPayment(double principal, int termMonths) {
        if (termMonths <= 0) {
            throw new IllegalArgumentException("Term must be positive");
        }
        if (principal <= 0) {
            throw new IllegalArgumentException("Principal must be positive");
        }

        double monthlyRate = interestRate / 12;
        double payment = principal * (monthlyRate * Math.pow(1 + monthlyRate, termMonths)) /
                         (Math.pow(1 + monthlyRate, termMonths) - 1);

        return Math.round(payment * 100.0) / 100.0;
    }

    public double calculateTotalInterest(double principal, int termMonths) {
        double monthlyPayment = calculateMonthlyPayment(principal, termMonths);
        return Math.round((monthlyPayment * termMonths - principal) * 100.0) / 100.0;
    }
}