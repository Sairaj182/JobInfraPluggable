package com.sairaj.jobinfra.server.repository;

import com.sairaj.jobinfra.server.domain.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findByUserId(Long userId);
}
