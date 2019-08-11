package com.kamal.profiles.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProfilesService {

	@Value("{}")
	private String checkForDuplicateEntry;
	
}
