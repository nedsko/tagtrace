package com.tagtrace.adapter.outbound.persistance.mapper;

import com.tagtrace.adapter.outbound.persistance.model.OwnerEntity;
import com.tagtrace.adapter.outbound.persistance.model.TagEntity;
import com.tagtrace.adapter.outbound.persistance.model.TagStatus;
import com.tagtrace.application.domain.model.entity.Owner;
import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntityToDomainMapper {
    public Tag mapTagEntity(TagEntity tagEntity) {
        return new Tag(
                mapToTagId(tagEntity.getId()),
                mapToOwnerId(tagEntity.getOwner()),
                mapTagStatus(tagEntity.getStatus()),
                tagEntity.getCreatedTimeStamp(),
                tagEntity.getLastModifiedTimeStamp(),
                mapToTagName(tagEntity.getName()),
                mapToGeoLocation(tagEntity.getLatitude(), tagEntity.getLongitude()));
    }

    public Owner mapOwnerEntity(OwnerEntity ownerEntity) {
        return new Owner(
                mapToOwnerId(ownerEntity),
                ownerEntity.getOwnedTags().stream().map(TagEntity::getId).map(this::mapToTagId).toList(),
                mapToOwnerName(ownerEntity.getName()),
                mapToEmail(ownerEntity.getEmail())
        );
    }

    public com.tagtrace.application.domain.model.value_object.TagStatus mapTagStatus(TagStatus persistenceTag) {
        return switch (persistenceTag) {
            case INACTIVE -> com.tagtrace.application.domain.model.value_object.TagStatus.INACTIVE;
            case ACTIVE -> com.tagtrace.application.domain.model.value_object.TagStatus.ACTIVE;
            case REPORTED_MISSING -> com.tagtrace.application.domain.model.value_object.TagStatus.REPORTED_MISSING;
            case DECOMMISSIONED -> com.tagtrace.application.domain.model.value_object.TagStatus.DECOMMISSIONED;
        };
    }

    public GeoLocation mapToGeoLocation(Double latitude, Double longitude) {
        if (latitude == null && longitude == null) {
            return null;
        }
        return new GeoLocation(latitude, longitude);
    }

    public TagId mapToTagId(UUID id) {
        return new TagId(id);
    }

    public TagName mapToTagName(String name) {
        return new TagName(name);
    }

    public OwnerId mapToOwnerId(OwnerEntity ownerEntity) {
        if (ownerEntity == null) {
            return null;
        }
        return new OwnerId(ownerEntity.getId());
    }

    public OwnerName mapToOwnerName(String name) {
        return new OwnerName(name);
    }

    public Email mapToEmail(String email) {
        return new Email(email);
    }
}
