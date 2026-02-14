# Functional Requirements

The system must support the following functionality:

1. Accept job submission requests from clients.
2. Persist job data before execution.
3. Execute jobs asynchronously using background workers.
4. Support multiple job types with different payloads.
5. Retry jobs automatically on failure.
6. Support delayed or scheduled job execution.
7. Track job status throughout its lifecycle.
8. Allow querying job status by job ID.
9. Prevent job loss in case of worker or service crashes.
