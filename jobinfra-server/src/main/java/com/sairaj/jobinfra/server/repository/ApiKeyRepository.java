package com.sairaj.jobinfra.server.repository;

import com.sairaj.jobinfra.server.domain.ApiKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, Long> {
    Optional<ApiKeyEntity> findByKeyHash(String keyHash);
    List<ApiKeyEntity> findByProjectId(Long projectId);
}
