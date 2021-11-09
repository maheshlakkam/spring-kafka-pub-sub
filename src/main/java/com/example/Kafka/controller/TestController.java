package com.example.Kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Kafka.producer.KafkaProducer;

@RestController
public class TestController {

	@Autowired
	KafkaProducer kafkaProducer;

	@GetMapping("/kafka/publish")
	public String sendMessage() {
		kafkaProducer.produce();
		return "success";
	}
}
