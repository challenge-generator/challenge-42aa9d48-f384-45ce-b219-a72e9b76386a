package com.pragma.loanprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
public class LoanProcessingApplication {
    private final LoanProcessingProperties loanProcessingProperties;

    public LoanProcessingApplication(LoanProcessingProperties loanProcessingProperties) {
        this.loanProcessingProperties = loanProcessingProperties;
        validateProperties();
    }

    private void validateProperties() {
        if (loanProcessingProperties.getMaxConcurrentRequests() <= 0) {
            throw new IllegalStateException("maxConcurrentRequests debe ser mayor que 0");
        }
        if (loanProcessingProperties.getRequestTimeout().isNegative() || loanProcessingProperties.getRequestTimeout().isZero()) {
            throw new IllegalStateException("requestTimeout debe ser un valor positivo");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(LoanProcessingApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerConfig defaultCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowSize(2)
                .build();
    }

    @Bean
    public TimeLimiterConfig defaultTimeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(loanProcessingProperties.getRequestTimeout().toMillis()))
                .build();
    }
}

record LoanProcessingProperties(
    int maxConcurrentRequests,
    Duration requestTimeout
) {}