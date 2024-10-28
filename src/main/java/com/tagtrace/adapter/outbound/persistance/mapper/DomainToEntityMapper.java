package com.tagtrace.adapter.outbound.persistance.mapper;

import com.tagtrace.adapter.outbound.persistance.model.TagEntity;
import com.tagtrace.adapter.outbound.persistance.model.TagStatus;
import com.tagtrace.application.domain.model.entity.Tag;
import org.springframework.stereotype.Component;

@Component
public class DomainToEntityMapper {
    public TagEntity mapToTagEntity(Tag tag) {
        return new TagEntity(
                null,
                mapToTagStatus(tag.getStatus()),
                tag.getCreatedTimeStamp(),
                tag.getLastModifiedTimeStamp(),
                tag.getName() != null ? tag.getName().value() : null,
                tag.getLastKnownLocation() != null ? tag.getLastKnownLocation().latitude() : null,
                tag.getLastKnownLocation() != null ? tag.getLastKnownLocation().longitude() : null
        );
    }

    public TagStatus mapToTagStatus(com.tagtrace.application.domain.model.value_object.TagStatus tagStatus) {
        return switch (tagStatus) {
            case ACTIVE -> TagStatus.ACTIVE;
            case REPORTED_MISSING -> TagStatus.REPORTED_MISSING;
        };
    }
}
