package com.tagtrace.application.domain.model.entity;

import com.tagtrace.application.domain.model.value_object.Email;
import com.tagtrace.application.domain.model.value_object.OwnerId;
import com.tagtrace.application.domain.model.value_object.OwnerName;
import com.tagtrace.application.domain.model.value_object.TagId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Owner {
    private final OwnerId id;
    private List<TagId> ownedTagIds;
    private OwnerName name;
    private Email email;

    public Owner(OwnerId id, List<TagId> ownedTagIds, OwnerName name, Email email) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        this.id = id;
        this.ownedTagIds = Objects.requireNonNullElseGet(ownedTagIds, ArrayList::new);
        this.name = name;
        this.email = email;
    }

    public static Owner newOwner(OwnerName name, Email email) {
        return new Owner(new OwnerId(UUID.randomUUID()), new ArrayList<>(), name, email);
    }

    public void setOwnedTagIds(List<TagId> ownedTagIds) {
        this.ownedTagIds = Objects.requireNonNullElseGet(ownedTagIds, ArrayList::new);
    }

    public void setName(OwnerName name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null in Owner");
        }
        this.name = name;
    }

    public void setEmail(Email email) {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null in Owner");
        }
        this.email = email;
    }
}
