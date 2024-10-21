package com.tagtrace.application.port.inbound.get_tag_details;

import com.tagtrace.application.domain.model.entity.Tag;
import com.tagtrace.application.domain.model.value_object.TagId;

public interface GetTagDetailsUseCase {
    Tag getTagById(TagId tagId);
}
