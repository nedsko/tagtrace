package com.tagtrace.application.domain.model.entity;

import com.tagtrace.application.domain.model.value_object.Email;
import com.tagtrace.application.domain.model.value_object.OwnerId;
import com.tagtrace.application.domain.model.value_object.OwnerName;
import com.tagtrace.application.domain.model.value_object.TagId;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Owner {
    private OwnerId id;
    private List<TagId> ownedTagIds;
    private OwnerName name;
    private Email email;

    public Owner(OwnerId id, List<TagId> ownedTagIds, OwnerName name, Email email) {
        this.id = id;
        this.ownedTagIds = ownedTagIds;
        this.name = name;
        this.email = email;
    }

    public static Owner newOwner(OwnerName name, Email email) {
        return new Owner(null, new ArrayList<>(), name, email);
    }
}
