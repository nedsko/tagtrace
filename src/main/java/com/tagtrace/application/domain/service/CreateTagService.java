package com.tagtrace.application.domain.service;

import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.TagName;
import com.tagtrace.application.port.inbound.create_tag.CreateTagOutput;
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
    public CreateTagOutput createTag(TagName tagName) {
        var newTag = Tag.createNewTag(tagName);
        var idOfNewTag = createTagPort.createTag(newTag);
        return new CreateTagOutput(idOfNewTag);
    }
}
