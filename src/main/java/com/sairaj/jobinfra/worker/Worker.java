package com.sairaj.jobinfra.worker;
import com.sairaj.jobinfra.core.Job;
import com.sairaj.jobinfra.core.JobStatus;
import com.sairaj.jobinfra.queue.JobQueue;
import com.sairaj.jobinfra.executor.JobExecutor;
import com.sairaj.jobinfra.executor.JobExecutorRegistry;

public class Worker implements Runnable {
    private final JobQueue queue;
    private final JobExecutorRegistry registry;
    public Worker(JobQueue queue, JobExecutorRegistry registry){
        this.queue = queue;
        this.registry = registry;
    }
    public void run(){
        while(true){
            Job job = null;
            try{
                job = queue.dequeue();
                job.setStatus(JobStatus.RUNNING);
                JobExecutor executor = registry.resolve(job.getHandler());
                executor.execute(job);
                job.setStatus(JobStatus.SUCCESS);
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