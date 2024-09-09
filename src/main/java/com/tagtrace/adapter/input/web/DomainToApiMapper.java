package com.tagtrace.adapter.input.web;

import com.tagtrace.application.port.input.CreateOwnerOutput;
import org.springframework.stereotype.Component;

@Component
public class DomainToApiMapper {
    CreateOwnerResponseObject toResponseObject(CreateOwnerOutput output) {
        var createdOwner = output.createdOwner();
        return new CreateOwnerResponseObject(
                createdOwner.getId().value(),
                createdOwner.getName().value(),
                createdOwner.getEmail().value()
        );
    }
}
