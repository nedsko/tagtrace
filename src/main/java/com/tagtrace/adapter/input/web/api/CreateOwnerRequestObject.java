package com.tagtrace.adapter.input.web.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateOwnerRequestObject(
        @NotNull
        @Size(min = 1, max = 60)
        String name,
        @NotNull
        @Email
        String email
) {
}
