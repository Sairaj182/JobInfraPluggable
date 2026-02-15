package com.sairaj.jobinfra.worker;
import com.sairaj.jobinfra.queue.JobQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.sairaj.jobinfra.executor.JobExecutorRegistry;
import jakarta.annotation.PreDestroy;

public class WorkerPool{
    private final ExecutorService executor;
    
    public WorkerPool(JobQueue queue, JobExecutorRegistry registry, int poolSize){
        this.executor = Executors.newFixedThreadPool(poolSize);
        for(int i=0; i<poolSize; i++){
            executor.submit(new Worker(queue, registry));
        }
    }
    @PreDestroy
    public void shutdown(){
        System.out.println("Shutting down Worker Pool...");
        executor.shutdown();
    }

}