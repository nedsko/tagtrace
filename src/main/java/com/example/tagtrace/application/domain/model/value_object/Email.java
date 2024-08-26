package com.example.tagtrace.application.domain.model.value_object;

import org.apache.commons.validator.routines.EmailValidator;

public record Email(String value) {
    public Email {
        if (!EmailValidator.getInstance().isValid(value)) {
            throw new IllegalArgumentException("Invalid email provided");
        }
    }
}
