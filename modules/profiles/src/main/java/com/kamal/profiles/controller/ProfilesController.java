package com.kamal.profiles.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamal.profiles.domain.Profile;
import com.kamal.profiles.repository.ProfilesRepository;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {

	@Autowired
	private ProfilesRepository profilesRepository;

	@PostMapping("/findid/{id}")
	public Profile findprofile(@PathVariable("id") long id) {
		System.out.println(id);
		Profile profile = null;
		Optional<Profile> temp = profilesRepository.findById(id);
		if (temp.isPresent()) {
			profile = temp.get();
		}
		return profile;
	}

	@PostMapping("/create")
	public String createPRofile(@RequestBody Profile profile) {
		String retVal = null;
		if (!profilesRepository.findById(profile.getId()).isPresent()) {
			profilesRepository.insert(profile);
			retVal = "Created";
		} else {
			retVal = "duplicate user id <" + profile.getId() + ">";
		}
		return retVal;
	}
}
