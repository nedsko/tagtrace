package com.tagtrace.application.port.outbound;

import com.tagtrace.application.domain.model.entity.Tag;

public interface UpdateTagPort {
    Tag updateTag(Tag updatedTag);
}
