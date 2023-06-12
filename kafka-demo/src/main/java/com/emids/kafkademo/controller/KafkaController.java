package com.emids.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emids.kafkademo.entity.Employee;
import com.emids.kafkademo.service.KafkaEmployeeProducer;
import com.emids.kafkademo.service.KafkaProducer;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

	@Autowired
	private KafkaProducer producer;
	
	@Autowired
	private KafkaEmployeeProducer employeeProducer;
	
	@GetMapping("/publish")
	public String sendMessage( @RequestParam("message") String message) {
		producer.publishToTopic(message);
		return "'"+message+ "' is published";
	}
	
	@GetMapping("/employee")
	public String publishEmployee(@RequestBody Employee employee) {
		employeeProducer.publishEmployee(employee);
		return "Employee published with employeeId"+employee.getEmployeeId();
	}
}
