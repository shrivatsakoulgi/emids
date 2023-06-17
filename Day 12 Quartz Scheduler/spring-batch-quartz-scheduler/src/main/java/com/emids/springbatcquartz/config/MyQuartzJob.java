package com.emids.springbatcquartz.config;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class MyQuartzJob extends QuartzJobBean{
	
	private String jobName;
	private JobLauncher jobLauncher;
	private JobLocator jobLocator;
	
//	@Autowired
//	private Job quartzJob;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		try {
			Job job = jobLocator.getJob(jobName);
			JobParameters parameters = new JobParametersBuilder()
					.addString("Company", "EMIDS")
					.addString("Time", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
			jobLauncher.run(job, parameters);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public JobLocator getJobLocator() {
		return jobLocator;
	}

	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	

}
