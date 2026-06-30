package com.sairaj.jobinfra.executor;

import com.sairaj.jobinfra.core.ExecutionType;
import com.sairaj.jobinfra.core.Job;
import org.springframework.stereotype.Component;

@Component
public class SpringHandlerExecutionStrategy implements JobExecutionStrategy {

    private final JobExecutorRegistry registry;

    public SpringHandlerExecutionStrategy(JobExecutorRegistry registry) {
        this.registry = registry;
    }

    @Override
    public ExecutionResult execute(Job job) {
        long startTime = System.currentTimeMillis();
        try {
            JobExecutor executor = registry.resolve(job.getHandler());
            executor.execute(job);
            long duration = System.currentTimeMillis() - startTime;
            return ExecutionResult.success(200, duration, "Success");
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            return ExecutionResult.failure(e.getMessage(), 500, duration, null);
        }
    }

    @Override
    public ExecutionType getSupportedType() {
        return ExecutionType.SPRING_HANDLER;
    }
}
