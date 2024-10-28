package com.tagtrace.adapter.outbound.persistance.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tags")
@Getter
@Setter
public class TagEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private TagStatus status;

    @Column(name = "created_timestamp", nullable = false)
    private Instant createdTimeStamp;

    @Column(name = "last_modified_timestamp", nullable = false)
    private Instant lastModifiedTimeStamp;

    @Column(name = "name", length = 40, nullable = false)
    @Nationalized
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    // For JPA only
    private TagEntity() {
    }

    public TagEntity(OwnerEntity owner,
                     TagStatus status,
                     Instant createdTimeStamp,
                     Instant lastModifiedTimeStamp,
                     String name,
                     Double latitude,
                     Double longitude) {
        this.owner = owner;
        this.status = status;
        this.createdTimeStamp = createdTimeStamp;
        this.lastModifiedTimeStamp = lastModifiedTimeStamp;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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