package com.sairaj.jobinfra.server.controller;

import com.sairaj.jobinfra.server.controller.dto.ApiResponse;
import com.sairaj.jobinfra.server.repository.UserRepository;
import com.sairaj.jobinfra.server.repository.ProjectRepository;
import com.sairaj.jobinfra.server.repository.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ObservabilityController {

    private final DataSource dataSource;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final JobRepository jobRepository;

    public ObservabilityController(DataSource dataSource, UserRepository userRepository, 
                                   ProjectRepository projectRepository, JobRepository jobRepository) {
        this.dataSource = dataSource;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.jobRepository = jobRepository;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<Map<String, Object>>> root() {
        Map<String, Object> metadata = new LinkedHashMap<>();
        metadata.put("name", "JobInfra Cloud");
        metadata.put("version", "1.0.0");
        metadata.put("status", "ACTIVE");
        metadata.put("documentationUrl", "/swagger-ui/index.html");
        metadata.put("healthUrl", "/health");
        return ResponseEntity.ok(ApiResponse.success(metadata));
    }

    @GetMapping("/version")
    public ResponseEntity<ApiResponse<Map<String, Object>>> version() {
        Map<String, Object> versionInfo = new LinkedHashMap<>();
        versionInfo.put("version", "1.0.0");
        versionInfo.put("buildTime", Instant.now().toString()); // Placeholder unless build-info is injected
        versionInfo.put("commit", "latest");
        return ResponseEntity.ok(ApiResponse.success(versionInfo));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, Object>>> health() {
        Map<String, Object> health = new LinkedHashMap<>();
        health.put("status", "UP");
        health.put("database", checkDatabase());
        health.put("uptimeSeconds", ManagementFactory.getRuntimeMXBean().getUptime() / 1000);
        health.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(ApiResponse.success(health));
    }

    @GetMapping("/api/v1/system/metrics")
    public ResponseEntity<ApiResponse<Map<String, Object>>> metrics() {
        Map<String, Object> metrics = new LinkedHashMap<>();
        metrics.put("database", checkDatabase());
        metrics.put("uptimeSeconds", ManagementFactory.getRuntimeMXBean().getUptime() / 1000);
        metrics.put("totalUsers", userRepository.count());
        metrics.put("totalProjects", projectRepository.count());
        metrics.put("totalJobs", jobRepository.count());
        // For job statuses, we could do count by status, but keep it simple for now as requested
        return ResponseEntity.ok(ApiResponse.success(metrics));
    }

    private String checkDatabase() {
        try (java.sql.Connection conn = dataSource.getConnection()) {
            if (conn.isValid(1)) {
                return "UP";
            }
        } catch (Exception e) {
            return "DOWN (" + e.getMessage() + ")";
        }
        return "DOWN";
    }
}
