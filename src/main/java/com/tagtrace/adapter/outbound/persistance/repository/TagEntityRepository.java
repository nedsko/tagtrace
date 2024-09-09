package com.tagtrace.adapter.outbound.persistance.repository;

import com.tagtrace.adapter.outbound.persistance.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagEntityRepository extends JpaRepository<TagEntity, UUID> {
}
