package com.sairaj.jobinfra.server.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApiKeyEntity> apiKeys;

    public ProjectEntity() {}

    public ProjectEntity(String name, UserEntity user) {
        this.name = name;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
    public List<ApiKeyEntity> getApiKeys() { return apiKeys; }
    public void setApiKeys(List<ApiKeyEntity> apiKeys) { this.apiKeys = apiKeys; }
}
