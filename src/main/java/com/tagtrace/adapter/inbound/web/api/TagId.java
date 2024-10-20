package com.tagtrace.adapter.inbound.web.api;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TagId(@NotNull UUID id) {
}
