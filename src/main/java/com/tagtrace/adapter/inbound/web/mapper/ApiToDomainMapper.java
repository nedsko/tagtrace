package com.tagtrace.adapter.inbound.web.mapper;

import com.tagtrace.adapter.inbound.web.api.CreateOwnerRequestObject;
import com.tagtrace.adapter.inbound.web.api.CreateTagRequestObject;
import com.tagtrace.adapter.inbound.web.api.PatchTagRequestObject;
import com.tagtrace.application.domain.model.value_object.*;
import com.tagtrace.application.port.inbound.create_owner.CreateOwnerInput;
import com.tagtrace.application.port.inbound.create_tag.CreateTagInput;
import com.tagtrace.application.port.inbound.update_tag.UpdateTagInput;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiToDomainMapper {

    default CreateOwnerInput toCreateOwnerInput(CreateOwnerRequestObject requestObject) {
        var name = new OwnerName(requestObject.name());
        var email = new Email(requestObject.email());
        return new CreateOwnerInput(name, email);
    }

    default CreateTagInput toCreateTagInput(CreateTagRequestObject requestObject) {
        return new CreateTagInput(new TagName(requestObject.name()), new OwnerId(requestObject.ownerId()));
    }

    default UpdateTagInput toUpdateTagInput(UUID id, PatchTagRequestObject requestObject) {
        var mappedStatus = toDomainTagStatus(requestObject.status());
        var mappedGeoLocation = toDomainGeoLocation(requestObject.location());
        var mappedId = new TagId(id);
        return new UpdateTagInput(mappedId, mappedStatus, mappedGeoLocation);
    }

    TagStatus toDomainTagStatus(com.tagtrace.adapter.inbound.web.api.TagStatus tagStatus);

    GeoLocation toDomainGeoLocation(com.tagtrace.adapter.inbound.web.api.GeoLocation geoLocation);
}
