package com.sairaj.jobinfra.server.controller.dto;

import java.time.Instant;

public class ApiKeyResponse {
    private Long id;
    private String rawKey;
    private String description;
    private Instant createdAt;

    public ApiKeyResponse() {}

    public ApiKeyResponse(Long id, String rawKey, String description, Instant createdAt) {
        this.id = id;
        this.rawKey = rawKey;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRawKey() { return rawKey; }
    public void setRawKey(String rawKey) { this.rawKey = rawKey; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
