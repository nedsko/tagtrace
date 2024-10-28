package com.tagtrace.adapter.inbound.web.api;

import java.util.UUID;

public record CreateOwnerResponseObject(UUID id, String name, String email) {}
