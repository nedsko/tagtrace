package com.tagtrace.application.domain.service;

import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.port.inbound.create_tag.CreateTagInput;
import com.tagtrace.application.port.inbound.create_tag.CreateTagUseCase;
import com.tagtrace.application.port.outbound.CreateTagPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CreateTagService implements CreateTagUseCase {

    private final CreateTagPort createTagPort;

    @Autowired
    public CreateTagService(CreateTagPort createTagPort) {
        this.createTagPort = createTagPort;
    }

    @Override
    public Tag createTag(CreateTagInput createTagInput) {
        var newTag = Tag.createNewTag(createTagInput.tagName(), createTagInput.ownerId());
        return createTagPort.createTag(newTag);
    }
}
