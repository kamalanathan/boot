package com.kamal.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	@Value(value = "${spring.kafka.demandtopic}")
	private String demandtopic;

	@Value(value = "${spring.kafka.supplytopic}")
	private String supplytopic;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendDemandMessage(String message) {
		logger.info(String.format("$$ -> Producing message --> %s", message));
		this.kafkaTemplate.send(demandtopic, message);
	}

	public void sendSupplyMessage(String message) {
		logger.info(String.format("$$ -> Producing message --> %s", message));
		this.kafkaTemplate.send(supplytopic, message);
	}

}