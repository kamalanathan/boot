package com.kamal.offers.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("offers")
public class Offers {
	@PrimaryKey
	private long id;
	private String offername;
	private String offertype;
	private double offerdiscountrate;
	private int offernumberofridesallowed;
	private String offerfortypeofuser;
}
