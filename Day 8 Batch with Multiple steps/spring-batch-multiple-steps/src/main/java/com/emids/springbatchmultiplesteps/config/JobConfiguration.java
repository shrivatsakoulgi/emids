package com.emids.springbatchmultiplesteps.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("My-Step1")
				.tasklet((StepContribution contribution,ChunkContext chunkContext) ->{
					System.out.println("Step1 : Finished Execution...");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("My-Step2")
				.tasklet((StepContribution contribution,ChunkContext chunkContext) ->{
					System.out.println("Step2 : Finished Execution...");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("My-Step3")
				.tasklet((StepContribution contribution,ChunkContext chunkContext) ->{
					System.out.println("Step3 : Finished Execution...");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("My-Job")
				.start(step1())
				.next(step2())
				.next(step3())
				.build();
	}
}
