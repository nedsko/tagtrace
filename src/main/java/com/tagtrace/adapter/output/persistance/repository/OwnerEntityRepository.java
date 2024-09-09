package com.tagtrace.adapter.output.persistance.repository;

import com.tagtrace.adapter.output.persistance.model.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OwnerEntityRepository extends JpaRepository<OwnerEntity, UUID> {
    Optional<OwnerEntity> findByEmail(String email);
}
