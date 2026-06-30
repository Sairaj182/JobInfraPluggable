package com.sairaj.jobinfra.worker;
import com.sairaj.jobinfra.core.Job;
import com.sairaj.jobinfra.core.JobStatus;
import com.sairaj.jobinfra.queue.JobQueue;
import com.sairaj.jobinfra.executor.JobExecutionStrategy;
import com.sairaj.jobinfra.executor.ExecutionResult;
import com.sairaj.jobinfra.core.ExecutionType;
import java.util.Map;

public class Worker implements Runnable {
    private final JobQueue queue;
    private final Map<ExecutionType, JobExecutionStrategy> strategyMap;
    
    public Worker(JobQueue queue, Map<ExecutionType, JobExecutionStrategy> strategyMap){
        this.queue = queue;
        this.strategyMap = strategyMap;
        System.out.println("Worker instance created: " + Thread.currentThread().getName());
    }
    public void run(){
        while(true){
            Job job = null;
            try{
                job = queue.dequeue();
                job.setStatus(JobStatus.RUNNING);
                
                ExecutionType type = job.getExecutionType() != null ? job.getExecutionType() : ExecutionType.SPRING_HANDLER;
                JobExecutionStrategy strategy = strategyMap.get(type);
                
                if (strategy == null) {
                    throw new IllegalStateException("No execution strategy found for type: " + type);
                }

                ExecutionResult result = strategy.execute(job);
                
                if (result.isSuccess()) {
                    job.setStatus(JobStatus.SUCCESS);
                } else {
                    throw new RuntimeException("Job execution failed: " + result.getErrorMessage());
                }
            }catch(Exception e){
                if(job==null) continue;
                job.incrementRetryCount();
                if(job.getRetryCount() <= job.getMaxRetries()){
                    job.setStatus(JobStatus.QUEUED);
                    queue.enqueue(job);
                    System.out.println("Retrying job " + job.getId());
                }else{
                    job.setStatus(JobStatus.FAILED);
                    System.out.println("Job permanently failed: " + job.getId());
                }
            }
        }
    }
}