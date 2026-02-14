# Tables we have:- 

job → truth & lifecycle (the *Job Store*) 

job_queue → scheduling & locking (used for *Fast Polling*) 

workers → health (alive/dead) & ownership (of job) 

job_attempts → execution history of jobs

applications → multi-tenant security (for ensuring workers )

dead_jobs → failure isolation and manual inspection