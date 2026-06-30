# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-07-01
### Added
- Monorepo restructuring to support Spring Boot Starter and Server separately.
- PostgreSQL persistence layer replacing in-memory execution states.
- JWT-based User Authentication and Registration.
- Project & API Key generation with SHA-256 hashed keys for database storage.
- Standardized `ApiResponse` wrappers for all endpoints.
- Request correlation tracing via `X-Request-ID` and MDC Logging.
- Enhanced Job lifecycle tracking with `startedAt`, `completedAt`, `updatedAt` and `CANCELLED` status.
- Observability endpoints (`/`, `/version`, `/health`, `/api/v1/system/metrics`).
- Multi-stage Dockerfile and Docker Compose environment.
- Complete OpenAPI Documentation via Swagger UI.
