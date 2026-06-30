package com.sairaj.jobinfra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sairaj.jobinfra.core.Job;
import com.sairaj.jobinfra.queue.JobQueue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobInfraApplication{

	public static void main(String[] args) {
		SpringApplication.run(JobInfraApplication.class, args);
	}

	@Bean
	public CommandLineRunner testRunner(JobQueue queue) {
		return args -> {

		};
	}
}
