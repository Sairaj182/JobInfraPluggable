# job_attempts

attempt_id (PK)
job_id (FK)
worker_id
started_at
finished_at
status
error_message

**Why?**
Jobs retry multiple times
Each retry is a separate attempt
Debugging & observability