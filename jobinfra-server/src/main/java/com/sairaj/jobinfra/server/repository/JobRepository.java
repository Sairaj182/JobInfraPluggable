package com.sairaj.jobinfra.server.repository;

import com.sairaj.jobinfra.server.domain.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, String> {
}
