package org.riphouse.config;

import org.aeonbits.owner.ConfigFactory;

public class LoaderConfig {

	public static volatile RipHouseConfig config;

	public static RipHouseConfig getConfig() {
		if (config == null) {
			synchronized (RipHouseConfig.class) {
				if (config == null) {
					config = ConfigFactory.create(RipHouseConfig.class);
				}
			}
		}
		return config;
	}

}
