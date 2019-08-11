package com.kamal.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.kamal.configs.KamalKafaConfiguration;
import com.kamal.domain.Demand;
import com.kamal.domain.Profile;
import com.kamal.domain.Supply;
import com.kamal.kafka.Producer;
import com.kamal.service.ProfilesServiceProxy;

@RestController
@RequestMapping(path = "/cars")
public class DemandAndSupplyCtrlr {
	@Autowired
	private Producer producer;

	@Autowired
	private ProfilesServiceProxy profProxy;

	@Autowired
	private KamalKafaConfiguration kafkaConfig;

	@PostMapping(path = "/demand")
	public String postDemand(@RequestBody Demand demand) {
		String retValue = null;
		if (!kafkaConfig.isBlockDemand()) {
			long id = demand.getRider_id();
			Map<String, Long> uriVariables = new HashMap<String, Long>();
			uriVariables.put("id", id);

			ResponseEntity<Profile> responseEntity = new RestTemplate()
					.postForEntity("http://master:6071/profiles/findid/" + id, null, Profile.class);

			Profile response = responseEntity.getBody();
			if (response == null || (response != null && response.getId() <= 0)) {
				retValue = "user id <" + demand.getRider_id() + "> not found";
			} else {
				Gson gson = new Gson();
				producer.sendDemandMessage(gson.toJson(demand));
				retValue = "posted-demand";
			}
			if (profProxy.findprofile(id) != null) {
				System.out.println("adasmbdsam");
			}
		} else {
			retValue = "Sorry request cannot be posted at this moment";
		}
		return retValue;
	}

	@PostMapping(path = "/demandfeign")
	public String postDemandFeign(@RequestBody Demand demand) {
		String retValue = null;
		if (!kafkaConfig.isBlockDemand()) {
			long id = demand.getRider_id();
			if (profProxy.findprofile(id) == null) {
				retValue = "user id <" + demand.getRider_id() + "> not found";
			} else {
				Gson gson = new Gson();
				producer.sendDemandMessage(gson.toJson(demand));
				retValue = "posted-demand";
			}
		} else {
			retValue = "Sorry request cannot be posted at this moment";
		}
		return retValue;
	}

	@PostMapping(path = "/supply")
	public String postDemand(@RequestBody Supply supply) {
		Gson gson = new Gson();
		producer.sendSupplyMessage(gson.toJson(supply));
		return "posted-supply";
	}

}