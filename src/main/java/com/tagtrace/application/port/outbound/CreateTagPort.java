package com.tagtrace.application.port.outbound;

import com.tagtrace.application.domain.model.entity.Tag;

public interface CreateTagPort {
    Tag createTag(Tag tagToCreate);
}
