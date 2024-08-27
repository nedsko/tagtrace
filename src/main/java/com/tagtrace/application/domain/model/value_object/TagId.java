package com.tagtrace.application.domain.model.value_object;

import java.util.UUID;

public record TagId(UUID value) {
    public TagId {
        if (value == null) {
            throw new IllegalArgumentException("Value of TagId cannot be null");
        }
    }
}
