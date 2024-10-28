package com.tagtrace.application.domain.event;

import com.tagtrace.application.domain.model.entity.Owner;

public record OwnerCreatedEvent(Owner owner) {}
