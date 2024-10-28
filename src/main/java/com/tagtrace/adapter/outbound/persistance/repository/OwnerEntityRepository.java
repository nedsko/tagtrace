package com.tagtrace.adapter.outbound.persistance.repository;

import com.tagtrace.adapter.outbound.persistance.model.OwnerEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerEntityRepository extends JpaRepository<OwnerEntity, UUID> {
    Optional<OwnerEntity> findByEmail(String email);
}
