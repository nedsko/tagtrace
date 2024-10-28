package com.tagtrace.adapter.inbound.web.api;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTagRequestObject(
        @NotNull
        String name,
        @NotNull
        UUID ownerId
) {
}
