package com.sairaj.jobinfra.core;

import java.time.Instant;
import java.util.UUID;

public class Job {
    private final String id;
    private String handler;
    private final String payload;
    private JobStatus status;
    private int retryCount;
    private final int maxRetries = 3;
    private final Instant createdAt;
    private Instant startedAt;
    private Instant completedAt;
    private Instant updatedAt;

    private ExecutionType executionType;
    private String webhookUrl;
    private java.util.Map<String, String> webhookHeaders;
    private String projectId;

    public Job(String handler, String payload){
        this.id = UUID.randomUUID().toString();
        this.executionType = ExecutionType.SPRING_HANDLER;
        this.handler = handler;
        this.payload = payload;
        this.status = JobStatus.CREATED;
        this.retryCount = 0;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public Job(ExecutionType executionType, String handler, String webhookUrl, java.util.Map<String, String> webhookHeaders, String projectId, String payload){
        this.id = UUID.randomUUID().toString();
        this.executionType = executionType != null ? executionType : ExecutionType.SPRING_HANDLER;
        this.handler = handler;
        this.webhookUrl = webhookUrl;
        this.webhookHeaders = webhookHeaders;
        this.projectId = projectId;
        this.payload = payload;
        this.status = JobStatus.CREATED;
        this.retryCount = 0;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public Job(String id, ExecutionType executionType, String handler, String webhookUrl, java.util.Map<String, String> webhookHeaders, String projectId, String payload, JobStatus status, int retryCount, 
               Instant createdAt, Instant startedAt, Instant completedAt, Instant updatedAt) {
        this.id = id;
        this.executionType = executionType != null ? executionType : ExecutionType.SPRING_HANDLER;
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
    public String getId() {
        return id;
    }
    public ExecutionType getExecutionType() {
        return executionType;
    }
    public void setExecutionType(ExecutionType executionType) {
        this.executionType = executionType;
    }
    public String getWebhookUrl() {
        return webhookUrl;
    }
    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }
    public java.util.Map<String, String> getWebhookHeaders() {
        return webhookHeaders;
    }
    public void setWebhookHeaders(java.util.Map<String, String> webhookHeaders) {
        this.webhookHeaders = webhookHeaders;
    }
    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getHandler() {
        return handler;
    }
    public void setHandler(String handler){
        this.handler = handler;
    }
    public String getPayload() {
        return payload;
    }
    public JobStatus getStatus() {
        return status;
    }
    public void setStatus(JobStatus status) {
        this.status = status;
    }
    public int getRetryCount() {
        return retryCount;
    }
    public void incrementRetryCount() {
        this.retryCount++;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public Instant getStartedAt() {
        return startedAt;
    }
    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }
    public Instant getCompletedAt() {
        return completedAt;
    }
    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }
    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
    public int getMaxRetries() {
        return maxRetries;
    }

}