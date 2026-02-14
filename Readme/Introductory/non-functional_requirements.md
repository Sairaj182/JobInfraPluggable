# Non-Functional Requirements

## Reliability
- Jobs must not be lost once persisted.
- The system must recover automatically after crashes.

## Scalability
- Support horizontal scaling of workers.

## Performance
- Job submission should be low latency.
- Workers should execute jobs concurrently.

## Consistency
- Provide at-least-once execution guarantees.
- Avoid invalid job state transitions.

## Fault Tolerance
- Handle worker crashes gracefully.
- Tolerate temporary queue or network failures.

## Observability
- Emit logs for job lifecycle events.
- Expose metrics for monitoring and alerting.
