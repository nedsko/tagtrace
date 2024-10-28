package com.tagtrace.application.domain.service;

import com.tagtrace.application.domain.event.OwnerCreatedEvent;
import com.tagtrace.application.domain.exception.DuplicateEntityException;
import com.tagtrace.application.domain.model.entity.Owner;
import com.tagtrace.application.port.inbound.create_owner.CreateOwnerInput;
import com.tagtrace.application.port.inbound.create_owner.CreateOwnerOutput;
import com.tagtrace.application.port.inbound.create_owner.CreateOwnerUseCase;
import com.tagtrace.application.port.outbound.LoadOwnersPort;
import com.tagtrace.application.port.outbound.SaveOwnerPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateOwnerService implements CreateOwnerUseCase {
    private final LoadOwnersPort loadOwnersPort;
    private final SaveOwnerPort saveOwnerPort;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public CreateOwnerService(
            LoadOwnersPort loadOwnersPort, SaveOwnerPort saveOwnerPort, ApplicationEventPublisher eventPublisher) {
        this.loadOwnersPort = loadOwnersPort;
        this.saveOwnerPort = saveOwnerPort;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CreateOwnerOutput createNewOwner(CreateOwnerInput input) {
        var newOwner = Owner.newOwner(input.name(), input.email());
        var ownerWithSameEmail = loadOwnersPort.findByEmail(input.email());
        if (ownerWithSameEmail != null) {
            throw new DuplicateEntityException("Owner with e-mail %s already exists"
                    .formatted(input.email().value()));
        }
        var savedOwner = saveOwnerPort.save(newOwner);
        eventPublisher.publishEvent(new OwnerCreatedEvent(savedOwner));
        return new CreateOwnerOutput(savedOwner);
    }
}
