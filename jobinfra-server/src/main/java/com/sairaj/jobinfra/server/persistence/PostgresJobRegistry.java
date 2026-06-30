package com.sairaj.jobinfra.server.persistence;

import com.sairaj.jobinfra.core.Job;
import com.sairaj.jobinfra.registry.JobRegistry;
import com.sairaj.jobinfra.server.domain.JobEntity;
import com.sairaj.jobinfra.server.repository.JobRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class PostgresJobRegistry implements JobRegistry {

    private final JobRepository jobRepository;
    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    public PostgresJobRegistry(JobRepository jobRepository, com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
        this.jobRepository = jobRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void register(Job job) {
        jobRepository.save(toEntity(job));
    }

    @Override
    public Job get(String id) {
        return jobRepository.findById(id).map(this::toJob).orElse(null);
    }

    @Override
    public Collection<Job> getAll() {
        return jobRepository.findAll().stream()
                .map(this::toJob)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        jobRepository.deleteById(id);
    }

    private Job toJob(JobEntity entity) {
        java.util.Map<String, String> headers = null;
        if (entity.getWebhookHeaders() != null) {
            try {
                headers = objectMapper.readValue(entity.getWebhookHeaders(), new com.fasterxml.jackson.core.type.TypeReference<java.util.Map<String, String>>() {});
            } catch (Exception e) {
                // Ignore parsing errors for now
            }
        }
        return new Job(
                entity.getId(),
                entity.getExecutionType(),
                entity.getHandler(),
                entity.getWebhookUrl(),
                headers,
                entity.getProjectId(),
                entity.getPayload(),
                entity.getStatus(),
                entity.getRetryCount(),
                entity.getCreatedAt(),
                entity.getStartedAt(),
                entity.getCompletedAt(),
                entity.getUpdatedAt()
        );
    }

    private JobEntity toEntity(Job job) {
        String headersJson = null;
        if (job.getWebhookHeaders() != null) {
            try {
                headersJson = objectMapper.writeValueAsString(job.getWebhookHeaders());
            } catch (Exception e) {
                // Ignore serialization errors
            }
        }
        return new JobEntity(
                job.getId(),
                job.getExecutionType(),
                job.getHandler(),
                job.getWebhookUrl(),
                headersJson,
                job.getProjectId(),
                job.getPayload(),
                job.getStatus(),
                job.getRetryCount(),
                job.getCreatedAt(),
                job.getStartedAt(),
                job.getCompletedAt(),
                job.getUpdatedAt()
        );
    }
}
