package com.tagtrace.adapter.output.persistance.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "owners")
@Getter
@Setter
public class OwnerEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private UUID id;

    @OneToMany(mappedBy = "owner")
    private List<TagEntity> ownedTags;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "email")
    private String email;

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
