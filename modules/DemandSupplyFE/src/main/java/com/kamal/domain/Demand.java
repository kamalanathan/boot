package com.kamal.domain;

import lombok.Data;

@Data
public class Demand {
	private long rider_id;
	private String name;
	private String phonenumber;
	private double latitude;
	private double longitude;
}
