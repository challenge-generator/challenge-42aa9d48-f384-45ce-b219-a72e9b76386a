package com.pragma.loanprocessing.infrastructure.exception;

public class LoanValidationException extends RuntimeException {
    private final String field;
    private final Object rejectedValue;

    public LoanValidationException(String message) {
        super(message);
        this.field = null;
        this.rejectedValue = null;
    }

    public LoanValidationException(String message, String field) {
        super(message);
        this.field = field;
        this.rejectedValue = null;
    }

    public LoanValidationException(String message, String field, Object rejectedValue) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public LoanValidationException(String message, Throwable cause) {
        super(message, cause);
        this.field = null;
        this.rejectedValue = null;
    }

    public LoanValidationException(String message, String field, Throwable cause) {
        super(message, cause);
        this.field = field;
        this.rejectedValue = null;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }
}