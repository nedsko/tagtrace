package com.tagtrace.application.port.inbound.create_tag;

import com.tagtrace.application.domain.model.value_object.TagName;

public interface CreateTagUseCase {
    CreateTagOutput createTag(TagName tagName);
}
