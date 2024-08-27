package com.tagtrace.application.domain.service;

import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.port.input.CreateTagOutput;
import com.tagtrace.application.port.input.CreateTagUseCase;
import com.tagtrace.application.port.output.CreateTagPort;
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
    public CreateTagOutput createTag() {
        var newTag = Tag.createNewTag();
        var idOfNewTag = createTagPort.createTag(newTag);
        return new CreateTagOutput(idOfNewTag);
    }
}
