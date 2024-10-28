package com.tagtrace.application.domain.service;

import com.tagtrace.application.domain.exception.MissingEntityException;
import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.port.inbound.update_tag.UpdateTagInput;
import com.tagtrace.application.port.inbound.update_tag.UpdateTagUseCase;
import com.tagtrace.application.port.outbound.LoadTagPort;
import com.tagtrace.application.port.outbound.UpdateTagPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateTagService implements UpdateTagUseCase {
    private final LoadTagPort loadTagPort;
    private final UpdateTagPort updateTagPort;

    @Autowired
    public UpdateTagService(LoadTagPort loadTagPort, UpdateTagPort updateTagPort) {
        this.loadTagPort = loadTagPort;
        this.updateTagPort = updateTagPort;
    }

    @Override
    public Tag updateTag(UpdateTagInput input) {
        var tagToUpdate = loadTagPort
                .getTagById(input.tagId())
                .orElseThrow(() -> new MissingEntityException("Could not update tag. There is tag with id %s"
                        .formatted(input.tagId().value())));
        if (input.newStatus() != null) {
            tagToUpdate.setStatus(input.newStatus());
        }
        if (input.newLocation() != null) {
            tagToUpdate.setLastKnownLocation(input.newLocation());
        }
        return updateTagPort.updateTag(tagToUpdate);
    }
}
