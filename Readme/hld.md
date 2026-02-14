# High-Level Components

The system consists of the following major components:

## Job Ingestion Service
- Accepts job submission requests.
- Validates input and persists jobs.
- Enqueues job **references** for processing.

## Job Store (Database)
- Acts as the source of truth for job data.
- Stores job *payload, state, and metadata*.
- Supports locking and retries.

## Queue / Dispatcher
- Decouples job producers from workers.
- Buffers job references.
- Enables parallel processing.

## Worker Engine
- Polls the queue for jobs.
- Fetches job details from the Job Store.
- Executes jobs safely.
- Updates job state.

## Job Handlers
- Contain business-specific logic.
- Implement a common execution interface.
- Remain independent of infra concerns.


# High-Level Data Flow

1. A client submits a job request to the *Job Ingestion Service*.
2. The service validates the request and persists the job in the *Job Store*
   with status *PENDING*.
3. The job *ID* is pushed to the *Queue*.
4. A Worker picks up the job ID from the Queue.
5. The Worker fetches the job from the Job Store.
6. The Worker *locks* the job and executes the corresponding *Job Handler*.
7. Upon completion:
   - *SUCCESS* → job marked as SUCCESS
   - *FAILURE* → job marked FAILED or scheduled for retry
8. Job state is updated in the Job Store.


# High-Level Architecture Diagram

Client / Service
      |
      v
Job Ingestion API
      |
      v
Job Store (Database)
      |
      v
Queue / Dispatcher
      |
      v
Worker Pool
      |
      v
Job Handlers (Business Logic handles this)

