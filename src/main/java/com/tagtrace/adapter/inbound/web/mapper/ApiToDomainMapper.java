package com.tagtrace.adapter.inbound.web.mapper;

import com.tagtrace.adapter.inbound.web.api.CreateOwnerRequestObject;
import com.tagtrace.adapter.inbound.web.api.CreateTagRequestObject;
import com.tagtrace.application.domain.model.value_object.Email;
import com.tagtrace.application.domain.model.value_object.OwnerId;
import com.tagtrace.application.domain.model.value_object.OwnerName;
import com.tagtrace.application.domain.model.value_object.TagName;
import com.tagtrace.application.port.inbound.create_owner.CreateOwnerInput;
import com.tagtrace.application.port.inbound.create_tag.CreateTagInput;
import org.springframework.stereotype.Component;

@Component
public class ApiToDomainMapper {

    public CreateOwnerInput toCreateOwnerInput(CreateOwnerRequestObject requestObject) {
        var name = new OwnerName(requestObject.name());
        var email = new Email(requestObject.email());
        return new CreateOwnerInput(name, email);
    }

    public CreateTagInput toCreateTagInput(CreateTagRequestObject requestObject) {
        return new CreateTagInput(
                new TagName(requestObject.name()),
                new OwnerId(requestObject.ownerId())
        );
    }
}
