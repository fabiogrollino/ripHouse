package org.riphouse.filters;

import java.io.UnsupportedEncodingException;

import javax.annotation.Priority;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.riphouse.annotations.Authorization;
import org.riphouse.token.InfoToken;
import org.riphouse.token.TokenHandler;

@Authorization
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) {
		try {
			TokenHandler tokenHandler = new TokenHandler();
			String token = tokenHandler.getStringToken(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION));
			InfoToken infoToken = tokenHandler.getInfoToken(token);
			
			//TODO implementare ulteriore logica di validazione del token
			
		} catch (UnsupportedEncodingException e) {
			//TODO loggare
			throw new InternalServerErrorException(e.getMessage());
		}
	}

}
