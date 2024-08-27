package com.tagtrace.application.domain.model.entity;

import com.tagtrace.application.domain.model.value_object.*;
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

    private Tag() {
    }

    public Tag(TagId id,
               OwnerId ownerId,
               TagStatus status,
               Instant createdTimeStamp,
               Instant lastModifiedTimeStamp,
               TagName name,
               GeoLocation lastKnownLocation) {
        this.id = id;
        this.ownerId = ownerId;
        this.status = status;
        this.createdTimeStamp = createdTimeStamp;
        this.lastModifiedTimeStamp = lastModifiedTimeStamp;
        this.name = name;
        this.lastKnownLocation = lastKnownLocation;
    }

    public static Tag createNewTag() {
        var tag = new Tag();
        tag.status = TagStatus.INACTIVE;
        tag.createdTimeStamp = Instant.now();
        tag.lastModifiedTimeStamp = Instant.now();
        return tag;
    }
}
