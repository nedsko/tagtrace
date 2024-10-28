package com.tagtrace.application.port.inbound.delete_tag;

import com.tagtrace.application.domain.model.value_object.TagId;

public interface DeleteTagUseCase {
    void deleteTag(TagId tagId);
}
