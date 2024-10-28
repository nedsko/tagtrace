package com.tagtrace.application.domain.service;

import com.tagtrace.application.domain.exception.MissingEntityException;
import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.TagId;
import com.tagtrace.application.port.inbound.get_tag_details.GetTagDetailsUseCase;
import com.tagtrace.application.port.outbound.LoadTagPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTagDetailsService implements GetTagDetailsUseCase {
    private final LoadTagPort loadTagPort;

    @Autowired
    public GetTagDetailsService(LoadTagPort loadTagPort) {
        this.loadTagPort = loadTagPort;
    }

    @Override
    public Tag getTagById(TagId tagId) {
        return loadTagPort.getTagById(tagId)
                .orElseThrow(() -> new MissingEntityException("Tag with id %s does not exist".formatted(tagId.value())));
    }
}
