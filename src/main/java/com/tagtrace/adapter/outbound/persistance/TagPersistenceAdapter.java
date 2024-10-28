package com.tagtrace.adapter.outbound.persistance;

import com.tagtrace.adapter.outbound.persistance.mapper.DomainToEntityMapper;
import com.tagtrace.adapter.outbound.persistance.mapper.EntityToDomainMapper;
import com.tagtrace.adapter.outbound.persistance.repository.OwnerEntityRepository;
import com.tagtrace.adapter.outbound.persistance.repository.TagEntityRepository;
import com.tagtrace.application.domain.exception.MissingEntityException;
import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.TagId;
import com.tagtrace.application.port.outbound.CreateTagPort;
import com.tagtrace.application.port.outbound.DeleteTagPort;
import com.tagtrace.application.port.outbound.LoadTagPort;
import com.tagtrace.application.port.outbound.UpdateTagPort;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagPersistenceAdapter implements CreateTagPort, LoadTagPort, DeleteTagPort, UpdateTagPort {
    private final TagEntityRepository tagEntityRepository;
    private final OwnerEntityRepository ownerEntityRepository;
    private final DomainToEntityMapper domainToEntityMapper;
    private final EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public TagPersistenceAdapter(
            TagEntityRepository tagEntityRepository,
            OwnerEntityRepository ownerEntityRepository,
            DomainToEntityMapper domainToEntityMapper,
            EntityToDomainMapper entityToDomainMapper) {
        this.tagEntityRepository = tagEntityRepository;
        this.ownerEntityRepository = ownerEntityRepository;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public Tag createTag(Tag tagToCreate) {
        var ownerEntity = ownerEntityRepository
                .findById(tagToCreate.getOwnerId().value())
                .orElseThrow(() -> new MissingEntityException("There is no owner with id %s"
                        .formatted(tagToCreate.getOwnerId().value())));
        var tagEntityToCreate = domainToEntityMapper.mapToTagEntity(tagToCreate);
        tagEntityToCreate.setOwner(ownerEntity);
        var persistedEntity = tagEntityRepository.save(tagEntityToCreate);
        return entityToDomainMapper.mapTagEntity(persistedEntity);
    }

    @Override
    public Optional<Tag> getTagById(TagId tagId) {
        return tagEntityRepository.findById(tagId.value()).map(entityToDomainMapper::mapTagEntity);
    }

    @Override
    public boolean doesTagExist(TagId tagId) {
        return tagEntityRepository.existsById(tagId.value());
    }

    @Override
    public void deleteTag(TagId tagId) {
        if (!tagEntityRepository.existsById(tagId.value())) {
            throw new MissingEntityException("Cannot delete tag. There is no tag with id %s".formatted(tagId.value()));
        }
        tagEntityRepository.deleteById(tagId.value());
    }

    @Override
    public Tag updateTag(Tag updatedTag) {
        var persistedTag = tagEntityRepository
                .findById(updatedTag.getId().value())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No tag with id %s".formatted(updatedTag.getId().value())));
        var mappedTagToUpdate = domainToEntityMapper.mapToTagEntity(updatedTag);
        persistedTag.updateFromTag(mappedTagToUpdate);
        return entityToDomainMapper.mapTagEntity(tagEntityRepository.save(persistedTag));
    }
}
