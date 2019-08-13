package com.kamal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kamal.configs.PropertyConfiguration;
import com.kamal.domain.Demand;
import com.kamal.kafka.Producer;
import com.kamal.service.ProfilesServiceProxy;

@RestController
@RequestMapping("/suggestoffers")
public class SuggestOffers {

	@Autowired
	private Producer producer;

	@Autowired
	PropertyConfiguration propertyConfiguration;

	@Autowired
	private ProfilesServiceProxy profProxy;

	@RequestMapping("/cabs")
	public String cars(@RequestBody Demand demand) {
		String retValue = null;
		if (!propertyConfiguration.isPropertyConfiguration()) {
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
}
