# Queue:-

job_id (PK)
app_id
status -> READY , LOCKED , DONE
locked_by -> worker ID
locked_at -> prevents double processing

Note: this table is for polling, not for history