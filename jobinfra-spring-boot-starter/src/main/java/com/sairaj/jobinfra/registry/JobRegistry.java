package com.sairaj.jobinfra.registry;

import com.sairaj.jobinfra.core.Job;
import java.util.Collection;

public interface JobRegistry {
    void register(Job job);
    Job get(String id);
    Collection<Job> getAll();
    void delete(String id);
}