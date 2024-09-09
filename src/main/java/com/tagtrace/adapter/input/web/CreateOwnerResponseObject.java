package com.tagtrace.adapter.input.web;

import java.util.UUID;

public record CreateOwnerResponseObject(
        UUID id,
        String name,
        String email
) {
}
