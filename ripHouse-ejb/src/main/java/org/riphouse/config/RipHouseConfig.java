package org.riphouse.config;

import java.util.Date;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.HotReload;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@Sources({
	"file:${RIPHOUSE_APP_CONFIG}/riphouse.properties"
})
@HotReload
@LoadPolicy(LoadType.MERGE)
public interface RipHouseConfig extends Config {

	@ConverterClass(ExpirationDateConverter.class)
	@DefaultValue("1800000")
	@Key("expiration.time.milliseconds")
	Date getExpirationDate();
	
	@Key("KEY_TOKEN_VALUE")
	String getKey();
	
	@DefaultValue("10")
	@Key("MAX_LOGIN_ATTEMPTS")
	int getMaxLoginAttempts();
	
	@DefaultValue("600000")
	@Key("TIME_FOR_RETRY")
	long getTimeForRetry();
	
	@DefaultValue("java:/jdbc/vecho")
	@Key("DATA_SORCE_NAME")
	String getDataSourceName();
}
