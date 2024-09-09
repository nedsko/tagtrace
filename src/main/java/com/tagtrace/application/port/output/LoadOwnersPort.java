package com.tagtrace.application.port.output;

import com.tagtrace.application.domain.model.entity.Owner;
import com.tagtrace.application.domain.model.value_object.Email;

public interface LoadOwnersPort {
    Owner findByEmail(Email email);
}
