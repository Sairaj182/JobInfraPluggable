package com.sairaj.jobinfra.worker;
import com.sairaj.jobinfra.queue.JobQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.sairaj.jobinfra.executor.JobExecutorRegistry;
import jakarta.annotation.PreDestroy;

import com.sairaj.jobinfra.executor.JobExecutionStrategy;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;
import com.sairaj.jobinfra.core.ExecutionType;

public class WorkerPool{
    private final ExecutorService executor;
    
    public WorkerPool(JobQueue queue, List<JobExecutionStrategy> strategies, int poolSize){
        this.executor = Executors.newFixedThreadPool(poolSize);
        Map<ExecutionType, JobExecutionStrategy> strategyMap = new EnumMap<>(ExecutionType.class);
        for (JobExecutionStrategy strategy : strategies) {
            strategyMap.put(strategy.getSupportedType(), strategy);
        }

        for(int i=0; i<poolSize; i++){
            executor.submit(new Worker(queue, strategyMap));
        }
    }
    @PreDestroy
    public void shutdown(){
        System.out.println("Shutting down Worker Pool...");
        executor.shutdown();
    }

}