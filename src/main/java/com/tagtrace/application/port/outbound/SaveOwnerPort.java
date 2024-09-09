package com.tagtrace.application.port.outbound;

import com.tagtrace.application.domain.model.entity.Owner;

public interface SaveOwnerPort {
    Owner save(Owner owner);
}
