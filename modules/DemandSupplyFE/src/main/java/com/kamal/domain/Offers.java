package com.kamal.domain;

import lombok.Data;

@Data
public class Offers {
	private long id;
	private String offername;
	private String offertype;
	private double offerdiscountrate;
	private int offernumberofridesallowed;
	private String offerfortypeofuser;
}
