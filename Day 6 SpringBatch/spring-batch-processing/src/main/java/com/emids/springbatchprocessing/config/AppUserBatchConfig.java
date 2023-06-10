package com.emids.springbatchprocessing.config;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.emids.springbatchprocessing.entity.AppUser;

@Configuration
public class AppUserBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean 
	public Job job(
			ItemReader<AppUser> itemReader,
			ItemProcessor<AppUser, AppUser> itemProcessor,
			ItemWriter<AppUser> itemWriter) {
		
		Step step = stepBuilderFactory.get("Load-File")		// name for Step
					.<AppUser, AppUser>chunk(100)
					.reader(itemReader)
					.processor(itemProcessor)
					.writer(itemWriter)
					.build();
		
		return jobBuilderFactory.get("User data Batch Processing")		// name for Job
								.incrementer(new RunIdIncrementer())	// keep track of steps
								.start(step)
								.build();
		
	}
	
	@Bean
	public FlatFileItemReader<AppUser> fileItemReader(@Value("${inputFilePath}") String filePath){

			Resource resource = new FileSystemResource(new File(filePath));
			FlatFileItemReader<AppUser> itemReader = new FlatFileItemReader<>();
			itemReader.setResource(resource);
			itemReader.setLinesToSkip(1);
			itemReader.setLineMapper(lineMapper());  	// Map Entry in CSV file to Entity Object
			// another scenario
			// read from Repository and send as List of Objects
			return itemReader;
	}

	@Bean
	public LineMapper<AppUser> lineMapper() {
	
		DefaultLineMapper<AppUser> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] {"id","name","department","age","salary"});
		
		
		BeanWrapperFieldSetMapper<AppUser> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(AppUser.class);
		
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		
		
		return defaultLineMapper;
	}
}
