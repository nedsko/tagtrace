package com.tagtrace.adapter.outbound.persistance.model;

import com.tagtrace.application.domain.model.entity.Owner;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "owners")
@Getter
@Setter
public class OwnerEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private UUID id;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<TagEntity> ownedTags = new ArrayList<>();

    @Nationalized
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @NaturalId
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    // for JPA only
    private OwnerEntity() {}

    private OwnerEntity(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static OwnerEntity from(Owner domainOwner) {
        var id = domainOwner.getId() != null ? domainOwner.getId().value() : null;
        var name = domainOwner.getName() != null ? domainOwner.getName().value() : null;
        var email = domainOwner.getEmail() != null ? domainOwner.getEmail().value() : null;
        return new OwnerEntity(id, name, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OwnerEntity other = (OwnerEntity) obj;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
