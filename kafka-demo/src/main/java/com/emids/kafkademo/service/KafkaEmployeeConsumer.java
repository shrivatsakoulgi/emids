package com.emids.kafkademo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.emids.kafkademo.entity.Employee;

@Service
public class KafkaEmployeeConsumer {

	@KafkaListener(topics = "employeedata",groupId = "myemployeegroup")
	public void consumeEmployee(Employee employee) {
		System.out.println("Employee data consumed:"+employee);
		// Persist this object
		// call repository.save method
	}
	
}
