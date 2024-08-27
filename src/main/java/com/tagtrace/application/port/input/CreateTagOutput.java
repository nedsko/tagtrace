package com.tagtrace.application.port.input;

import com.tagtrace.application.domain.model.value_object.TagId;

public record CreateTagOutput(TagId tagId) {
    public CreateTagOutput {
        if (tagId == null) {
            throw new IllegalArgumentException("TagId cannot be null");
        }
    }
}
