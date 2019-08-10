package com.kamal.matcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kamal.matcher.domain.Demand;
import com.kamal.matcher.domain.Supply;
import com.kamal.matcher.repo.DemandRepository;
import com.kamal.matcher.repo.SupplyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {

	@Autowired
	private SupplyRepository supplyRepository;

	@Autowired
	private DemandRepository demandRepository;

	@KafkaListener(topics = "supplytopiccars", containerFactory = "supplyKafkaListenerContainerFactory")
	public void consume(String message) {
		log.info(String.format("$$ -> Consumed Message -> %s", message));
		Supply s = new Supply();
		JsonElement element = new JsonParser().parse(message);
		s.setDriver_id(element.getAsJsonObject().get("driver_id").getAsLong());
		s.setName(element.getAsJsonObject().get("name").getAsString());
		s.setPhonenumber(element.getAsJsonObject().get("phonenumber").getAsString());
		s.setLatitude(element.getAsJsonObject().get("latitude").getAsDouble());
		s.setLongitude(element.getAsJsonObject().get("longitude").getAsDouble());
		supplyRepository.save(s);
	}

	@KafkaListener(topics = "demandtopic", containerFactory = "demandKafkaListenerContainerFactory")
	public void consumedemand(String message) {
		log.info(String.format("$$ -> Consumed Message -> %s", message));
		Demand s = new Demand();
		JsonElement element = new JsonParser().parse(message);
		s.setRider_id(Long.parseLong(element.getAsJsonObject().get("rider_id").getAsString()));
		s.setName(element.getAsJsonObject().get("name").getAsString());
		s.setPhonenumber(element.getAsJsonObject().get("phonenumber").getAsString());
		s.setLatitude(element.getAsJsonObject().get("latitude").getAsDouble());
		s.setLongitude(element.getAsJsonObject().get("longitude").getAsDouble());
		demandRepository.save(s);

	}
}