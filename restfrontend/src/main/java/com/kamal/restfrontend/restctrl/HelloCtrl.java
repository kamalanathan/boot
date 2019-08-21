package com.kamal.restfrontend.restctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/frontend")
public class HelloCtrl {

	@Autowired
	private OffersServiceProxyNoZuul offersServiceProxyNoZuul;

	@GetMapping(path = "/entrypoint")
	public String entryPoint() {
		return offersServiceProxyNoZuul.getData();
	}

	@GetMapping(path = "/entrypoint2")
	public String entryPoint2() {
		return offersServiceProxyNoZuul.getData();
	}

}
