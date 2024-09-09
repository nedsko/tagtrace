package com.tagtrace.application.port.output;

import com.tagtrace.application.domain.model.entity.Owner;

public interface SaveOwnerPort {
    Owner save(Owner owner);
}
