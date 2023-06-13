package com.emids.springbatchmultiplesteps;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchMultipleStepsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchMultipleStepsApplication.class, args);
	}

}
