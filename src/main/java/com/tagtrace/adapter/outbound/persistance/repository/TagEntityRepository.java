package com.tagtrace.adapter.outbound.persistance.repository;

import com.tagtrace.adapter.outbound.persistance.model.TagEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagEntityRepository extends JpaRepository<TagEntity, UUID> {}
