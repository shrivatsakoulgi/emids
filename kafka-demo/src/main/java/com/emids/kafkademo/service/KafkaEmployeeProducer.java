package com.emids.kafkademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.emids.kafkademo.entity.Employee;

@Service
public class KafkaEmployeeProducer {
	
	private static final String topic="employeedata";
	
	@Autowired
	KafkaTemplate<String, Employee> kafkaTemplate;
	
	public void publishEmployee(Employee employee) {
		kafkaTemplate.send(topic, employee);
	}

}
