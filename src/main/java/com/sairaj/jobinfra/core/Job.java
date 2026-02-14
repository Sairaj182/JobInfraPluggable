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

    public Job(String handler, String payload){
        this.id = UUID.randomUUID().toString();
        this.handler = handler;
        this.payload = payload;
        this.status = JobStatus.CREATED;
        this.retryCount = 0;
        this.createdAt = Instant.now();
    }
    public String getId() {
        return id;
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
    public int getMaxRetries() {
        return maxRetries;
    }

}