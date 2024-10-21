package com.tagtrace.adapter.inbound.web.api;

import jakarta.validation.constraints.NotNull;

public record TagName(@NotNull String name) {
}
