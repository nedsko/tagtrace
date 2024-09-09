package com.tagtrace.adapter.inbound.web.mapper;

import com.tagtrace.adapter.inbound.web.api.CreateOwnerRequestObject;
import com.tagtrace.application.domain.model.value_object.Email;
import com.tagtrace.application.domain.model.value_object.OwnerName;
import com.tagtrace.application.port.inbound.create_owner.CreateOwnerInput;
import org.springframework.stereotype.Component;

@Component
public class ApiToDomainMapper {

    public CreateOwnerInput toCreateOwnerInput(CreateOwnerRequestObject requestObject) {
        var name = new OwnerName(requestObject.name());
        var email = new Email(requestObject.email());
        return new CreateOwnerInput(name, email);
    }
}
