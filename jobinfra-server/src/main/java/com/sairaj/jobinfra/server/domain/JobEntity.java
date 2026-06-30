package com.sairaj.jobinfra.server.domain;

import com.sairaj.jobinfra.core.JobStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.Instant;

@Entity
@Table(name = "jobs")
public class JobEntity {
    @Id
    private String id;
    private String handler;
    private String payload;
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    private int retryCount;
    private Instant createdAt;
    private Instant startedAt;
    private Instant completedAt;
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private com.sairaj.jobinfra.core.ExecutionType executionType;
    private String webhookUrl;
    private String webhookHeaders;
    private String projectId;

    public JobEntity() {}

    public JobEntity(String id, com.sairaj.jobinfra.core.ExecutionType executionType, String handler, String webhookUrl, String webhookHeaders, String projectId, String payload, JobStatus status, int retryCount, 
                     Instant createdAt, Instant startedAt, Instant completedAt, Instant updatedAt) {
        this.id = id;
        this.executionType = executionType;
        this.handler = handler;
        this.webhookUrl = webhookUrl;
        this.webhookHeaders = webhookHeaders;
        this.projectId = projectId;
        this.payload = payload;
        this.status = status;
        this.retryCount = retryCount;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.updatedAt = updatedAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public com.sairaj.jobinfra.core.ExecutionType getExecutionType() { return executionType; }
    public void setExecutionType(com.sairaj.jobinfra.core.ExecutionType executionType) { this.executionType = executionType; }

    public String getWebhookUrl() { return webhookUrl; }
    public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }

    public String getWebhookHeaders() { return webhookHeaders; }
    public void setWebhookHeaders(String webhookHeaders) { this.webhookHeaders = webhookHeaders; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getHandler() { return handler; }
    public void setHandler(String handler) { this.handler = handler; }

    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }

    public int getRetryCount() { return retryCount; }
    public void setRetryCount(int retryCount) { this.retryCount = retryCount; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getStartedAt() { return startedAt; }
    public void setStartedAt(Instant startedAt) { this.startedAt = startedAt; }

    public Instant getCompletedAt() { return completedAt; }
    public void setCompletedAt(Instant completedAt) { this.completedAt = completedAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
