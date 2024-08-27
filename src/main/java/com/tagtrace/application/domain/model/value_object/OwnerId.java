package com.tagtrace.application.domain.model.value_object;

import java.util.UUID;

public record OwnerId(UUID value) {
    public OwnerId {
        if (value == null) {
            throw new IllegalArgumentException("Value of OwnerId cannot be null");
        }
    }
}
