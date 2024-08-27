package com.tagtrace.application.domain.model.entity;

import com.tagtrace.application.domain.model.value_object.Email;
import com.tagtrace.application.domain.model.value_object.OwnerId;
import com.tagtrace.application.domain.model.value_object.OwnerName;
import com.tagtrace.application.domain.model.value_object.TagId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Owner {
    private OwnerId id;
    private List<TagId> ownedTagIds;
    private OwnerName name;
    private Email email;
}
