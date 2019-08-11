package com.kamal.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("block")
public class PropertyConfiguration {

	private boolean blockDemand;

	public boolean isBlockDemand() {
		return blockDemand;
	}

	public void setBlockDemand(boolean blockDemand) {
		this.blockDemand = blockDemand;
	}

}
