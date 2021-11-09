package com.example.Kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducer {
	Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${topicName}")
	private String topicName;

	public void produce() {
		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, "Test Message");
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				logger.info("Message has been published offset=[{}]", result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Error in publishing message {}", ex.getMessage());
			}
		});
		;
	}

}
