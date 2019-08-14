package com.kamal.offers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamal.offers.domain.Offers;
import com.kamal.offers.repository.OffersRepository;

@RestController
@RequestMapping("/offers")
public class OffersController {

	@Autowired
	private OffersRepository repo;

	@GetMapping("/getallvalidoffers")
	public List<Offers> getAllValidOffers() {
		return repo.findAll();
	}

	@PostMapping("/createoffers")
	public void createOffers(@RequestBody Offers offers) {
		repo.insert(offers);
	}
}
