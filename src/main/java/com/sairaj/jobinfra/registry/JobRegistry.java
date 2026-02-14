package com.sairaj.jobinfra.registry;

import com.sairaj.jobinfra.core.Job;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JobRegistry{
    private final ConcurrentHashMap<String,Job> jobs = new ConcurrentHashMap<>();

    public void register(Job job){
        jobs.put(job.getId(),job);
    }
    public Job get(String id){
        return jobs.get(id);
    }
    public Collection<Job> getAll(){
        return jobs.values();
    }
}