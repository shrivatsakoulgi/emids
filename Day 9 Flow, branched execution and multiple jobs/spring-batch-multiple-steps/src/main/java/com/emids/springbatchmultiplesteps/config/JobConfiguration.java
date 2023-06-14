package com.emids.springbatchmultiplesteps.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
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
					System.out.println("Step1 : Reading from file...");
					System.out.println("Step1 : Finished...");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("My-Step2")
				.tasklet((StepContribution contribution,ChunkContext chunkContext) ->{
					System.out.println("Step2 : Updating to DB...");	
					System.out.println("Step2 : Finished Execution...");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("My-Step3")
				.tasklet((StepContribution contribution,ChunkContext chunkContext) ->{
					System.out.println("Step3 : Closing the file..");
					System.out.println("Step3 : Update the file First..");
					System.out.println("Step3 : Finished Execution...");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step commonStep() {
		return stepBuilderFactory.get("Common-Step")
				.tasklet((StepContribution contribution,ChunkContext chunkContext) ->{
					System.out.println("Initializing the application..");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public Step commonStep2() {
		return stepBuilderFactory.get("Common-Step2")
				.tasklet((StepContribution contribution,ChunkContext chunkContext) ->{
					String userId=chunkContext.getStepContext().getStepExecution()
					.getJobParameters().getString("userId");
					System.out.println("Initializing the application with Job2..");
					System.out.println("UserId:"+userId);
					return RepeatStatus.FINISHED;
				}).build();
	}
	
	@Bean
	public JobExecutionDecider decider() {
		return ((JobExecution jobExecution,StepExecution stepExecution) -> {
			System.out.println("Decider::Checking File Status..");
			boolean isFileUpdated = false;		// some logic to concur true/false
			return isFileUpdated ? new FlowExecutionStatus("COMPLETED"):new FlowExecutionStatus("FAILED");
			//FlowExecutionStatus()
			// STOPPED, UNKNOWN
		}
		);
	}
	
	@Bean
	public Flow sendNotificationFlow() {
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("Notification Flow");
		return flowBuilder.start(commonStep())
				.next(decider()).on("COMPLETED").to(step1()).next(step2())
				.from(decider()).on("FAILED").to(step3()).build();
	}
	
	
	@Bean("my-job")
	public Job job(Flow sendNotificationFlow) {
		return jobBuilderFactory.get("My-Job")
				.start(sendNotificationFlow).end()
				.build();
	}
	
	@Bean("job2")
	public Job job2() {
		return jobBuilderFactory.get("Job2")
				.start(commonStep2())
				.build();
	}
}
