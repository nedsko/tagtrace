package com.tagtrace.application.domain.model.entity;

import com.tagtrace.application.domain.model.value_object.*;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Tag {
    private final TagId id;
    private final OwnerId ownerId;
    private TagStatus status;
    private final Instant createdTimeStamp;
    private Instant lastModifiedTimeStamp;
    private TagName name;
    private GeoLocation lastKnownLocation;

    public Tag(
            TagId id,
            OwnerId ownerId,
            TagStatus status,
            Instant createdTimeStamp,
            Instant lastModifiedTimeStamp,
            TagName name,
            GeoLocation lastKnownLocation) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(ownerId);
        Objects.requireNonNull(status);
        Objects.requireNonNull(createdTimeStamp);
        Objects.requireNonNull(lastModifiedTimeStamp);
        Objects.requireNonNull(name);
        if (createdTimeStamp.isAfter(lastModifiedTimeStamp)) {
            throw new IllegalArgumentException("createdTimeStamp cannot be before lastModifiedTimeStamp");
        }
        this.id = id;
        this.ownerId = ownerId;
        this.status = status;
        this.createdTimeStamp = createdTimeStamp;
        this.lastModifiedTimeStamp = lastModifiedTimeStamp;
        this.name = name;
        this.lastKnownLocation = lastKnownLocation;
    }

    public static Tag createNewTag(
            OwnerId ownerId,
            TagStatus status,
            Instant createdTimeStamp,
            Instant lastModifiedTimeStamp,
            TagName name,
            GeoLocation lastKnownLocation) {
        return new Tag(
                new TagId(UUID.randomUUID()),
                ownerId,
                status,
                createdTimeStamp,
                lastModifiedTimeStamp,
                name,
                lastKnownLocation);
    }

    public static Tag createNewTagInDefaultState(TagName name, OwnerId ownerId) {
        return Tag.createNewTag(ownerId, TagStatus.ACTIVE, Instant.now(), Instant.now(), name, null);
    }

    public void setStatus(TagStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("status cannot be null in a Tag");
        }
        this.status = status;
        lastModifiedTimeStamp = Instant.now();
    }

    public void setName(TagName name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null in a Tag");
        }
        this.name = name;
        lastModifiedTimeStamp = Instant.now();
    }

    public void setLastKnownLocation(GeoLocation lastKnownLocation) {
        if (this.lastKnownLocation != null && lastKnownLocation == null) {
            throw new IllegalArgumentException(
                    "Cannot set lastKnownLocation to null if there is a lastKnownLocation defined");
        }
        this.lastKnownLocation = lastKnownLocation;
        lastModifiedTimeStamp = Instant.now();
    }
}
