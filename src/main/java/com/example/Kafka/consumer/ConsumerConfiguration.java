package com.example.Kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class ConsumerConfiguration {

	@Autowired
	KafkaProperties kafkaProperties;

	@Value("${bootstrap.server}")
	private String bootStrapServer;

	@Value("${consumer.count}")
	private int consumerCount;

	@Value("${group.id}")
	private String groupID;

	@Bean
	public Map<String, Object> consumerConfig() {
		Map<String, Object> props = new HashMap<String, Object>(kafkaProperties.buildConsumerProperties());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return props;
	}

	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		return new DefaultKafkaConsumerFactory<String, Object>(consumerConfig());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListnerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<String, Object>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(consumerCount);
		return factory;
	}
}
