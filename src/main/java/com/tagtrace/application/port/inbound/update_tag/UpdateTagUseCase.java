package com.tagtrace.application.port.inbound.update_tag;

import com.tagtrace.application.domain.model.entity.Tag;

public interface UpdateTagUseCase {
    Tag updateTag(UpdateTagInput input);
}
