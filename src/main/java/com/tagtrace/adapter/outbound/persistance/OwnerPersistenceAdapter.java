package com.tagtrace.adapter.outbound.persistance;

import com.tagtrace.adapter.outbound.persistance.mapper.EntityToDomainMapper;
import com.tagtrace.adapter.outbound.persistance.model.OwnerEntity;
import com.tagtrace.adapter.outbound.persistance.repository.OwnerEntityRepository;
import com.tagtrace.application.domain.model.entity.Owner;
import com.tagtrace.application.domain.model.value_object.Email;
import com.tagtrace.application.port.outbound.LoadOwnersPort;
import com.tagtrace.application.port.outbound.SaveOwnerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerPersistenceAdapter implements LoadOwnersPort, SaveOwnerPort {
    private final OwnerEntityRepository ownerEntityRepository;
    private final EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public OwnerPersistenceAdapter(
            OwnerEntityRepository ownerEntityRepository, EntityToDomainMapper entityToDomainMapper) {
        this.ownerEntityRepository = ownerEntityRepository;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public Owner findByEmail(Email email) {
        return ownerEntityRepository
                .findByEmail(email.value())
                .map(entityToDomainMapper::mapOwnerEntity)
                .orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        var ownerEntityToPersist = OwnerEntity.from(owner);
        return entityToDomainMapper.mapOwnerEntity(ownerEntityRepository.save(ownerEntityToPersist));
    }
}
