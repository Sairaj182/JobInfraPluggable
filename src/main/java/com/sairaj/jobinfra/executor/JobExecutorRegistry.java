package com.sairaj.jobinfra.executor;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JobExecutorRegistry{

    private final Map<String, JobExecutor> executors = new HashMap<>();

    public JobExecutorRegistry(List<JobExecutor> executorList){
        System.out.println("Discovered Executors:");
        for(JobExecutor executor : executorList){
            System.out.println(" -> " + executor.getHandlerName());
            executors.put(executor.getHandlerName(),executor);
        }
    }

    public JobExecutor resolve(String handlerName){
        JobExecutor executor = executors.get(handlerName);
        if (executor == null) {
            throw new IllegalArgumentException("No executor registered for handler: " + handlerName);
        }
        return executor;
    }
}
