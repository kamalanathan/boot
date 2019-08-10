package com.kamal.matcher.domain;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("rider")
public class Demand {
	@PrimaryKey
	private long rider_id;
	@Column
	private String name;
	@Column
	private String phonenumber;
	@Column
	private double latitude;
	@Column
	private double longitude;
}
