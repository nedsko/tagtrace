package com.tagtrace.application.port.inbound.create_tag;

import com.tagtrace.application.domain.model.entity.Tag;

public interface CreateTagUseCase {
    Tag createTag(CreateTagInput createTagInput);
}
