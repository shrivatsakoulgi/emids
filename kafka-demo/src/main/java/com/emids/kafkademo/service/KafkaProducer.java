package com.emids.kafkademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	private static final String topic="emids2";
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	public void publishToTopic(String message) {
		System.out.println("Publishing Message: '"+message+
				"' to topic: '"+topic+"'");
		kafkaTemplate.send(topic, message);
	}
}
