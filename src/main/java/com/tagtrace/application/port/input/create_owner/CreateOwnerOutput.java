package com.tagtrace.application.port.input.create_owner;

import com.tagtrace.application.domain.model.entity.Owner;

public record CreateOwnerOutput(Owner createdOwner) {
    public CreateOwnerOutput {
        if (createdOwner == null) {
            throw new IllegalArgumentException("createdOwner cannot be null");
        }
    }
}
