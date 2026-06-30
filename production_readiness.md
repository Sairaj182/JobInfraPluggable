# Production Readiness Review

## 1. Security Concerns
- **Password Strength**: There are no password complexity checks during user registration.
- **JWT Expiration & Refresh**: The system uses a simple JWT without refresh tokens. If a token is stolen, there's no way to invalidate it immediately without an infrastructure overhaul (like a blacklist).
- **API Key Scopes**: Currently, API keys have full access to their respective projects. Role-based or scope-based access (e.g., read-only) should be considered.
- **HTTPS/TLS**: The current application runs on HTTP. Production deployments must be fronted by an Application Load Balancer or Nginx configured with valid SSL/TLS certificates.

## 2. Scalability Bottlenecks
- **In-Memory Rate Limiter**: The current rate limiter uses a `ConcurrentHashMap` which works per-node. For multi-node AWS deployments (e.g., ECS), this will cause inconsistent rate limiting. A Redis-backed rate limiter is needed for distributed scalability.
- **Synchronous Execution**: The starter currently executes jobs inline or with simple thread pools. As the platform grows, decoupling submission from execution via external messaging queues (e.g., SQS, RabbitMQ, Kafka) is necessary.

## 3. Code Smells & Missing Validations
- **Error Types**: The `GlobalExceptionHandler` catches raw `Exception` for fallback, which can leak stack traces if misconfigured. We now use standard wrappers, but catching specific exceptions (e.g., `DataIntegrityViolationException`) explicitly is better.
- **Pagination**: Endpoints like `GET /api/v1/jobs` or `GET /api/v1/projects` return full lists. Pagination and limits must be implemented to prevent out-of-memory errors on large projects.

## 4. Performance Concerns
- **Database Indexes**: The `findByKeyHash` operation relies on a full scan unless an index is explicitly created. We should add a unique index on `key_hash` in `ApiKeyEntity`.
- **Flyway Migrations**: As the schema grows, ensure migrations are optimized and non-blocking for large datasets (e.g., creating indexes `CONCURRENTLY`).

## 5. Recommended Next Steps
- Implement Redis for distributed rate-limiting and job queuing.
- Implement pagination for all `GET` collection endpoints.
- Add database indexes to critical lookup columns like `key_hash` and `username`.
- Introduce a mechanism for users to rotate API keys easily.
