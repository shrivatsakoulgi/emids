package com.emids.springbatcquartz.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class MyQuartzConfig {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobLocator jobLocator;
	

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobRegistry jobRegistry;
	
	
	@Bean
	public Step step() {
		return stepBuilderFactory.get("My-Quartz-Step")
				.tasklet((StepContribution contribution,ChunkContext chunkContext) ->{
					System.out.println("Quartz Job Running...");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Job quartzJob() {
		return jobBuilderFactory.get("quartzJob")		// job name
				.start(step())
				.build();
	}

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
		JobRegistryBeanPostProcessor jobPostProcessor = new JobRegistryBeanPostProcessor();
		jobPostProcessor.setJobRegistry(jobRegistry);
		return jobPostProcessor;
	}
	
	
	@Bean
	public JobDetail jobDetail() {
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "quartzJob");
		jobDataMap.put("jobLauncher", jobLauncher);
		jobDataMap.put("jobLocator", jobLocator);
		
		return JobBuilder.newJob(MyQuartzJob.class)
				.setJobData(jobDataMap)
				.storeDurably()
				.build();
	}
	
	@Bean
	public Trigger jobTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInSeconds(20)		// run every 20 seconds
				.repeatForever();
		
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail())			// which Job to run
				.withIdentity("QuarzJobTrigger")		// Name of Trigger
				.withSchedule(scheduleBuilder)		// When to run (Schedule)
				.build();
	}
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setTriggers(jobTrigger());
		schedulerFactory.setQuartzProperties(quartzProperties());
		schedulerFactory.setJobDetails(jobDetail());
		return schedulerFactory;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation(new ClassPathResource("/quartz.properties"));
		properties.afterPropertiesSet();
		return properties.getObject();
	}
}
