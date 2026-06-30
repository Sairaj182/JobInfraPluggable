package com.sairaj.jobinfra.executor;

import com.sairaj.jobinfra.core.Job;

public interface JobExecutor {
    String getHandlerName();
    void execute(Job job) throws Exception;
}