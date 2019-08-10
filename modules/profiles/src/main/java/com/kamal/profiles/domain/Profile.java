package com.kamal.profiles.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("profiles")
public class Profile {
	@PrimaryKey
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