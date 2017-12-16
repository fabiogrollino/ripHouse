package org.riphouse.config;

import java.lang.reflect.Method;
import java.util.Date;

import org.aeonbits.owner.Converter;

public class ExpirationDateConverter implements Converter<Date> {

	@Override
	public Date convert(Method method, String expirationTimeMilliseconds) {
		long expirationDate = System.currentTimeMillis() + Long.parseLong(expirationTimeMilliseconds);
		return new Date(expirationDate);
	}

}
