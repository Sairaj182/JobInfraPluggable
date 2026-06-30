ALTER TABLE jobs ADD COLUMN execution_type VARCHAR(50) DEFAULT 'SPRING_HANDLER';
ALTER TABLE jobs ADD COLUMN webhook_url VARCHAR(2048);
ALTER TABLE jobs ADD COLUMN webhook_headers TEXT;
ALTER TABLE jobs ADD COLUMN project_id VARCHAR(255);

-- Update existing jobs
UPDATE jobs SET execution_type = 'SPRING_HANDLER' WHERE execution_type IS NULL;
