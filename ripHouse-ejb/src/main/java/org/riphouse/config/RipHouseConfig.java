package org.riphouse.config;

import java.util.Date;

import org.aeonbits.owner.Config;

public interface RipHouseConfig extends Config {

	@ConverterClass(ExpirationDateConverter.class)
	@Key("expiration.time.milliseconds")
	Date getExpirationDate();
	
	@Key("KEY_TOKEN_VALUE")
	String getKey();
}
