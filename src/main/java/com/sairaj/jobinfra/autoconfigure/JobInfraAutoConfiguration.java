package com.sairaj.jobinfra.autoconfigure;

import com.sairaj.jobinfra.queue.*;
import com.sairaj.jobinfra.worker.*;
import com.sairaj.jobinfra.registry.*;
import com.sairaj.jobinfra.executor.*;
import com.sairaj.jobinfra.service.*;
import com.sairaj.jobinfra.properties.JobInfraProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.List;

@Configuration
@EnableConfigurationProperties(JobInfraProperties.class)
public class JobInfraAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public JobQueue jobQueue() {
        return new InMemoryJobQueue();
    }

    @Bean
    @ConditionalOnMissingBean
    public JobRegistry jobRegistry() {
        return new JobRegistry();
    }

    @Bean
    @ConditionalOnMissingBean
    public JobExecutorRegistry jobExecutorRegistry(List<JobExecutor> executors) {
        return new JobExecutorRegistry(executors);
    }

    @Bean
    @ConditionalOnMissingBean
    public JobService jobService(JobQueue queue, JobRegistry registry) {
        return new JobService(queue, registry);
    }

    @Bean
    public WorkerPool workerPool(JobQueue queue,JobExecutorRegistry registry,JobInfraProperties properties) {
        int poolSize = properties.getWorker().getCount();
        return new WorkerPool(queue, registry, poolSize);
    }

}
