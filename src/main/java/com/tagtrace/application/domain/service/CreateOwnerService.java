package com.tagtrace.application.domain.service;

import com.tagtrace.application.domain.exception.DuplicateEntityException;
import com.tagtrace.application.domain.model.entity.Owner;
import com.tagtrace.application.port.input.create_owner.CreateOwnerInput;
import com.tagtrace.application.port.input.create_owner.CreateOwnerOutput;
import com.tagtrace.application.port.input.create_owner.CreateOwnerUseCase;
import com.tagtrace.application.port.output.LoadOwnersPort;
import com.tagtrace.application.port.output.SaveOwnerPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CreateOwnerService implements CreateOwnerUseCase {
    private final LoadOwnersPort loadOwnersPort;
    private final SaveOwnerPort saveOwnerPort;

    @Autowired
    public CreateOwnerService(LoadOwnersPort loadOwnersPort, SaveOwnerPort saveOwnerPort) {
        this.loadOwnersPort = loadOwnersPort;
        this.saveOwnerPort = saveOwnerPort;
    }

    @Override
    public CreateOwnerOutput createNewOwner(CreateOwnerInput input) {
        var newOwner = Owner.newOwner(input.name(), input.email());
        var ownerWithSameEmail = loadOwnersPort.findByEmail(input.email());
        if (ownerWithSameEmail != null) {
            throw new DuplicateEntityException("Owner with e-mail %s already exists".formatted(input.email().value()));
        }
        var savedOwner = saveOwnerPort.save(newOwner);
        return new CreateOwnerOutput(savedOwner);
    }
}
