package com.example.tagtrace.application.domain.model.value_object;

public record TagName(String value) {
    public TagName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Tag names cannot be null or blank");
        }
        if (value.length() > 40) {
            throw new IllegalArgumentException("Tag names cannot be longer than 40 characters");
        }
    }
}
