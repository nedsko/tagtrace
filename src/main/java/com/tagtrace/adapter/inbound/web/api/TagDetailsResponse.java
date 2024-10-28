package com.tagtrace.adapter.inbound.web.api;

import java.time.Instant;
import java.util.UUID;

public record TagDetailsResponse(
        UUID id,
        TagStatus status,
        Instant createdTimeStamp,
        Instant lastModifiedTimeStamp,
        String name,
        GeoLocation lastKnownLocation) {}
