package com.tagtrace.application.port.inbound.create_tag;

import com.tagtrace.application.domain.model.value_object.OwnerId;
import com.tagtrace.application.domain.model.value_object.TagName;

public record CreateTagInput(
        TagName tagName,
        OwnerId ownerId
) {
    public CreateTagInput {
        if (tagName == null) {
            throw new IllegalArgumentException("tagName cannot be null");
        }
        if (ownerId == null) {
            throw new IllegalArgumentException("ownerId cannot be null");
        }
    }
}
