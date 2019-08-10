package com.kamal.matcher.domain;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("cabs")
public class Supply {
	@PrimaryKey
	private long driver_id;
	@Column
	private String name;
	@Column
	private String phonenumber;
	@Column
	private double latitude;
	@Column
	private double longitude;
}
