package com.tagtrace.application.port.output;

import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.TagId;

public interface CreateTagPort {
    TagId createTag(Tag tagToCreate);
}
