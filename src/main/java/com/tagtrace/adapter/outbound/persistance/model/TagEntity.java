package com.tagtrace.adapter.outbound.persistance.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "tags")
@Getter
@Setter
public class TagEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false, updatable = false)
    private OwnerEntity owner;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private TagStatus status;

    @Column(name = "created_timestamp", nullable = false, updatable = false)
    private Instant createdTimeStamp;

    @Column(name = "last_modified_timestamp", nullable = false)
    private Instant lastModifiedTimeStamp;

    @Column(name = "name", length = 40, nullable = false, updatable = false)
    @Nationalized
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    // For JPA only
    private TagEntity() {}

    public TagEntity(
            UUID id,
            OwnerEntity owner,
            TagStatus status,
            Instant createdTimeStamp,
            Instant lastModifiedTimeStamp,
            String name,
            Double latitude,
            Double longitude) {
        this.id = id;
        this.owner = owner;
        this.status = status;
        this.createdTimeStamp = createdTimeStamp;
        this.lastModifiedTimeStamp = lastModifiedTimeStamp;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void updateFromTag(TagEntity tagEntityWithNewValues) {
        setStatus(tagEntityWithNewValues.getStatus());
        setLatitude(tagEntityWithNewValues.getLatitude());
        setLongitude(tagEntityWithNewValues.getLongitude());
        setLastModifiedTimeStamp(tagEntityWithNewValues.getLastModifiedTimeStamp());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TagEntity other = (TagEntity) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
