package com.example.tagtrace.application.domain.model.value_object;

public record OwnerName(String value) {
    public OwnerName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Owner names cannot be null or blank");
        }
        if (value.length() > 60) {
            throw new IllegalArgumentException("Owner names cannot be longer than 60 characters");
        }
    }
}
