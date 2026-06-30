package com.sairaj.jobinfra.registry;

import com.sairaj.jobinfra.core.Job;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryJobRegistry implements JobRegistry {
    private final ConcurrentHashMap<String,Job> jobs = new ConcurrentHashMap<>();

    @Override
    public void register(Job job){
        jobs.put(job.getId(),job);
    }

    @Override
    public Job get(String id){
        return jobs.get(id);
    }

    @Override
    public Collection<Job> getAll(){
        return jobs.values();
    }

    @Override
    public void delete(String id) {
        jobs.remove(id);
    }
}
