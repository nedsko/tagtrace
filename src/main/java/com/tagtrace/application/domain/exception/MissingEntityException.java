package com.tagtrace.application.domain.exception;

public class MissingEntityException extends RuntimeException {
    public MissingEntityException(String message) {
        super(message);
    }
}
