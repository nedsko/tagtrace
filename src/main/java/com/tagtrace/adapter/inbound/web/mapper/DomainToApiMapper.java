package com.tagtrace.adapter.inbound.web.mapper;

import com.tagtrace.adapter.inbound.web.api.CreateOwnerResponseObject;
import com.tagtrace.adapter.inbound.web.api.GeoLocation;
import com.tagtrace.adapter.inbound.web.api.TagDetailsResponse;
import com.tagtrace.adapter.inbound.web.api.TagStatus;
import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.TagId;
import com.tagtrace.application.domain.model.value_object.TagName;
import com.tagtrace.application.port.inbound.create_owner.CreateOwnerOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DomainToApiMapper {
    default CreateOwnerResponseObject toResponseObject(CreateOwnerOutput output) {
        var createdOwner = output.createdOwner();
        return new CreateOwnerResponseObject(
                createdOwner.getId().value(),
                createdOwner.getName().value(),
                createdOwner.getEmail().value()
        );
    }

    @Mapping(target = "id", source = "id", qualifiedByName = "tagIdMapper")
    @Mapping(target = "name", source = "name", qualifiedByName = "tagNameMapper")
    TagDetailsResponse toTagDetailsResponse(Tag tag);

    TagStatus toApiTagStatus(com.tagtrace.application.domain.model.value_object.TagStatus domainTagStatus);

    GeoLocation toApiGeoLocation(com.tagtrace.application.domain.model.value_object.GeoLocation domainGeoLocation);

    @Named("tagIdMapper")
    default UUID toUUID(TagId tagId) {
        return tagId.value();
    }

    @Named("tagNameMapper")
    default String toString(TagName tagName) {
        return tagName.value();
    }
}
