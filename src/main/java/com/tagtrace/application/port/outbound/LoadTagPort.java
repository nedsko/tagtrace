package com.tagtrace.application.port.outbound;

import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.TagId;

import java.util.Optional;

public interface LoadTagPort {
    Optional<Tag> getTagById(TagId tagId);

    boolean doesTagExist(TagId tagId);
}
