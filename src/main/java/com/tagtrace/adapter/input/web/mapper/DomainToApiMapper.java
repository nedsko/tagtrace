package com.tagtrace.adapter.input.web.mapper;

import com.tagtrace.adapter.input.web.api.CreateOwnerResponseObject;
import com.tagtrace.application.port.input.create_owner.CreateOwnerOutput;
import org.springframework.stereotype.Component;

@Component
public class DomainToApiMapper {
    public CreateOwnerResponseObject toResponseObject(CreateOwnerOutput output) {
        var createdOwner = output.createdOwner();
        return new CreateOwnerResponseObject(
                createdOwner.getId().value(),
                createdOwner.getName().value(),
                createdOwner.getEmail().value()
        );
    }
}
