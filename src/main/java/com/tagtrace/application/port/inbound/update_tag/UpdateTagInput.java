package com.tagtrace.application.port.inbound.update_tag;

import com.tagtrace.application.domain.exception.InvalidUpdateException;
import com.tagtrace.application.domain.model.value_object.GeoLocation;
import com.tagtrace.application.domain.model.value_object.TagId;
import com.tagtrace.application.domain.model.value_object.TagStatus;

public record UpdateTagInput(TagId tagId, TagStatus newStatus, GeoLocation newLocation) {
    public UpdateTagInput {
        if (tagId == null) {
            throw new IllegalArgumentException("tagId cannot be null in UpdateTagInput");
        }
        if (newStatus == null && newLocation == null) {
            throw new InvalidUpdateException("Invalid update. At least one change to the tag has to be specified");
        }
    }
}
