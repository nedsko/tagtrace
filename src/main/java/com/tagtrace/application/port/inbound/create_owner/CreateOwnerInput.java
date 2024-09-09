package com.tagtrace.application.port.inbound.create_owner;

import com.tagtrace.application.domain.model.value_object.Email;
import com.tagtrace.application.domain.model.value_object.OwnerName;

public record CreateOwnerInput(OwnerName name, Email email) {
    public CreateOwnerInput {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
    }
}
