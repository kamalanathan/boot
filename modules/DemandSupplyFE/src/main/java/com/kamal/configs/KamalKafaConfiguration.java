package com.kamal.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KamalKafaConfiguration {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;

	@Value(value = "${block.demands}")
	private boolean blockDemand;

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> config = new HashMap<String, Object>();

		// config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
		// "master:9092,slave02:9092,slave04:9092");
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return new DefaultKafkaProducerFactory<String, String>(config);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<String, String>(producerFactory());
	}

	@Bean
	public boolean isBlockDemand() {
		return blockDemand;
	}
}