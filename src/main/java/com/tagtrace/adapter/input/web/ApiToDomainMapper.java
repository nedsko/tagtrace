package com.tagtrace.adapter.input.web;

import com.tagtrace.application.domain.model.value_object.Email;
import com.tagtrace.application.domain.model.value_object.OwnerName;
import com.tagtrace.application.port.input.CreateOwnerInput;
import org.springframework.stereotype.Component;

@Component
public class ApiToDomainMapper {

    public CreateOwnerInput toCreateOwnerInput(CreateOwnerRequestObject requestObject) {
        var name = new OwnerName(requestObject.name());
        var email = new Email(requestObject.email());
        return new CreateOwnerInput(name, email);
    }
}
