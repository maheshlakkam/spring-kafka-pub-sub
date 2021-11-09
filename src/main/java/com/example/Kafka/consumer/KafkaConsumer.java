package com.example.Kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "${topicName}", groupId = "${group.id}")
	public void consume(String message) {
		logger.info(message);
	}
}
