package com.kamal.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.kamal.configs.PropertyConfiguration;
import com.kamal.domain.Demand;
import com.kamal.domain.Offers;
import com.kamal.domain.Profile;
import com.kamal.domain.Supply;
import com.kamal.kafka.Producer;
import com.kamal.service.OffersServiceProxyNoZuul;
import com.kamal.service.ProfilesServiceProxy;
import com.kamal.service.ProfilesServiceProxyNoZuul;

@RestController
@RequestMapping(path = "/cars")
public class DemandAndSupplyCtrlr {
	@Autowired
	private Producer producer;

	@Autowired
	private ProfilesServiceProxy profProxy;

	@Autowired
	PropertyConfiguration propertyConfiguration;

	@Autowired
	private ProfilesServiceProxyNoZuul profilesServiceProxyNoZuul;

	@Autowired
	private OffersServiceProxyNoZuul offersServiceProxyNoZuul;

	@PostMapping(path = "/demand")
	public String postDemand(@RequestBody Demand demand) {
		String retValue = null;
		if (!propertyConfiguration.isPropertyConfiguration()) {
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

	@PostMapping(path = "/demandallfezuul")
	public String postDemandAllZuul(@RequestBody Demand demand) {
		String retValue = null;
		if (!propertyConfiguration.isPropertyConfiguration()) {
			long id = demand.getRider_id();
			if (profilesServiceProxyNoZuul.findprofile(id) == null) {
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

	@PostMapping(path = "/demandallzuul")
	public String postDemandFEZuul(@RequestBody Demand demand) {
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

	@PostMapping(path = "/supply")
	public String postDemand(@RequestBody Supply supply) {
		Gson gson = new Gson();
		producer.sendSupplyMessage(gson.toJson(supply));
		return "posted-supply";
	}

	@PostMapping(path = "/demandwithsuggestions")
	public String postDemandwithsuggestions(@RequestBody Demand demand) {
		String retValue = null;
		if (!propertyConfiguration.isPropertyConfiguration()) {
			long id = demand.getRider_id();
			if (profilesServiceProxyNoZuul.findprofile(id) == null) {
				retValue = "user id <" + demand.getRider_id() + "> not found";
			} else {
				Gson gson = new Gson();
				producer.sendDemandMessage(gson.toJson(demand));
				List<Offers> offers = offersServiceProxyNoZuul.getallvalidoffers();
				retValue = "posted-demand";
				for (int i = 0; i < offers.size(); i++) {
					retValue += offers.get(i);
				}
			}
		} else {
			retValue = "Sorry request cannot be posted at this moment";
		}
		return retValue;
	}

	@PostMapping(path = "/demandallfezuul2")
	public String postDemandAllZuul2(@RequestBody Demand demand) {
		String retValue = null;
		retValue = "Sorry request cannot be posted at this moment";
		return retValue;
	}

	@GetMapping(path = "/test")
	public String postDemandAllZuul2() {
		String retValue = null;
		retValue = "test api";
		return retValue;
	}
}