package com.tagtrace.application.port.outbound;

import com.tagtrace.application.domain.model.entity.Owner;
import com.tagtrace.application.domain.model.value_object.Email;

public interface LoadOwnersPort {
    Owner findByEmail(Email email);
}
