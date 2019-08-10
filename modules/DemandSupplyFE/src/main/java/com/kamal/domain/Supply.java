package com.kamal.domain;

import lombok.Data;

@Data
public class Supply {
	private long driver_id;
	private String name;
	private String phonenumber;
	private double latitude;
	private double longitude;
}
