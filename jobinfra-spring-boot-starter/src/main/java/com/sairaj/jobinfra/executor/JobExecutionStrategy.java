package com.sairaj.jobinfra.executor;

import com.sairaj.jobinfra.core.ExecutionType;
import com.sairaj.jobinfra.core.Job;

public interface JobExecutionStrategy {
    ExecutionResult execute(Job job);
    ExecutionType getSupportedType();
}
