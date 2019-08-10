package com.kamal.domain;

import lombok.Data;

@Data
public class Profile {
	private long id;
	private String firstname;
	private String lastname;
	private String phonenumber;
	private String username;
	private String password;
	private String emailid;
	private String isactive;
	private String isblocked;
}