package com.example.tagtrace.application.domain.model.entity;

import com.example.tagtrace.application.domain.model.value_object.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Tag {
    private TagId id;
    private OwnerId ownerId;
    private TagStatus status;
    private Instant createdTimeStamp;
    private Instant lastModifiedTimeStamp;
    private TagName name;
    private GeoLocation lastKnownLocation;
}
