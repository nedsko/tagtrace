package com.tagtrace.adapter.outbound.persistance;

import com.tagtrace.adapter.outbound.persistance.mapper.DomainToEntityMapper;
import com.tagtrace.adapter.outbound.persistance.mapper.EntityToDomainMapper;
import com.tagtrace.adapter.outbound.persistance.repository.TagEntityRepository;
import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.TagId;
import com.tagtrace.application.port.outbound.CreateTagPort;
import com.tagtrace.application.port.outbound.LoadTagPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TagPersistenceAdapter implements CreateTagPort, LoadTagPort {
    private final TagEntityRepository tagEntityRepository;
    private final DomainToEntityMapper domainToEntityMapper;
    private final EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public TagPersistenceAdapter(TagEntityRepository tagEntityRepository,
                                 DomainToEntityMapper domainToEntityMapper,
                                 EntityToDomainMapper entityToDomainMapper) {
        this.tagEntityRepository = tagEntityRepository;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public TagId createTag(Tag tagToCreate) {
        var mappedEntity = domainToEntityMapper.mapToTagEntity(tagToCreate);
        var persistedEntity = tagEntityRepository.save(mappedEntity);
        return new TagId(persistedEntity.getId());
    }

    @Override
    public Optional<Tag> getTagById(TagId tagId) {
        return tagEntityRepository.findById(tagId.value())
                .map(entityToDomainMapper::mapTagEntity);
    }

    @Override
    public boolean doesTagExist(TagId tagId) {
        return tagEntityRepository.existsById(tagId.value());
    }
}
