package com.emids.springbatchmultiplesteps.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class MyStepJobController {
	
	@Autowired
	private JobLauncher jobLauncher;
		
	@Autowired
	@Qualifier("my-job")
	private Job job;
	
	@Autowired
	@Qualifier("job2")
	private Job job2;
	
	
	
	@GetMapping("/multiple-steps")
	public BatchStatus executeSteps() throws JobExecutionAlreadyRunningException, 
	JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("company", new JobParameter("EMIDS"));
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job, parameters);
		System.out.println("Job Status:"+jobExecution.getStatus());
		return jobExecution.getStatus();
		
	}
	
	@GetMapping("/multiple-steps/job2/{userId}")
	public BatchStatus executeJob2(@PathVariable String userId) throws JobExecutionAlreadyRunningException, 
	JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("company", new JobParameter("EMIDS"));
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		maps.put("userId", new JobParameter(userId));
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job2, parameters);
		System.out.println("Job Status:"+jobExecution.getStatus());
		return jobExecution.getStatus();
		
	}

}
