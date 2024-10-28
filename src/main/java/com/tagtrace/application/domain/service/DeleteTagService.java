package com.tagtrace.application.domain.service;

import com.tagtrace.application.domain.model.value_object.TagId;
import com.tagtrace.application.port.inbound.delete_tag.DeleteTagUseCase;
import com.tagtrace.application.port.outbound.DeleteTagPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DeleteTagService implements DeleteTagUseCase {
    private final DeleteTagPort deleteTagPort;

    @Autowired
    public DeleteTagService(DeleteTagPort deleteTagPort) {
        this.deleteTagPort = deleteTagPort;
    }

    @Override
    public void deleteTag(TagId tagId) {
        deleteTagPort.deleteTag(tagId);
    }
}
