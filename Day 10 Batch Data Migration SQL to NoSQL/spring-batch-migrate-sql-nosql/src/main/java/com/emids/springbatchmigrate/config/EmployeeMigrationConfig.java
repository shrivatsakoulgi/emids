package com.emids.springbatchmigrate.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emids.springbatchmigrate.entity.EmployeeDocument;
import com.emids.springbatchmigrate.entity.EmployeeEntity;

@Configuration
public class EmployeeMigrationConfig {
	

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job job(ItemReader<EmployeeEntity> itemReader,
				   ItemProcessor<EmployeeEntity, EmployeeDocument> itemProcessor,
				   ItemWriter<EmployeeDocument> itemWriter) {
		
		Step step = stepBuilderFactory.get("SQL to NoSQL")
				.<EmployeeEntity, EmployeeDocument>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		
		return jobBuilderFactory.get("Data Migration")		// name for Job
				.incrementer(new RunIdIncrementer())	// keep track of steps
				.start(step)
				.build();
	}

}
