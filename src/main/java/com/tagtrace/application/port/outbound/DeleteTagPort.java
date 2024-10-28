package com.tagtrace.application.port.outbound;

import com.tagtrace.application.domain.model.value_object.TagId;

public interface DeleteTagPort {
    void deleteTag(TagId tagId);
}
