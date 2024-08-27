package com.tagtrace.adapter.output.persistance;

import com.tagtrace.adapter.output.persistance.repository.TagEntityRepository;
import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.TagId;
import com.tagtrace.application.port.output.CreateTagPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagPersistenceAdapter implements CreateTagPort {
    private final TagEntityRepository tagEntityRepository;
    private final DomainToEntityMapper domainToEntityMapper;

    @Autowired
    public TagPersistenceAdapter(TagEntityRepository tagEntityRepository,
                                 DomainToEntityMapper domainToEntityMapper) {
        this.tagEntityRepository = tagEntityRepository;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public TagId createTag(Tag tagToCreate) {
        var mappedEntity = domainToEntityMapper.mapToTagEntity(tagToCreate);
        var persistedEntity = tagEntityRepository.save(mappedEntity);
        return new TagId(persistedEntity.getId());
    }
}
