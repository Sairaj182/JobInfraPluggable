package com.sairaj.jobinfra.service;

import com.sairaj.jobinfra.core.Job;
import com.sairaj.jobinfra.queue.JobQueue;
import com.sairaj.jobinfra.registry.JobRegistry;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class JobService{

    private final JobQueue queue;
    private final JobRegistry registry;

    public JobService(JobQueue queue,JobRegistry registry){
        this.queue = queue;
        this.registry = registry;
    }

    public String submit(String handlerName,String payload){
        Job job = new Job(handlerName, payload);
        registry.register(job);
        queue.enqueue(job);
        return job.getId();
    }

    public Job get(String id){
        return registry.get(id);
    }

    public Collection<Job> getAll(){
        return registry.getAll();
    }
}