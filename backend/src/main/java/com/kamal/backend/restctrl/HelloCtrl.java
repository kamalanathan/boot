package com.kamal.backend.restctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/backend")
public class HelloCtrl {

	@GetMapping(path = "/entrypoint")
	public String entryPoint() {
		return "backend Entry point vlaue";
	}
}
