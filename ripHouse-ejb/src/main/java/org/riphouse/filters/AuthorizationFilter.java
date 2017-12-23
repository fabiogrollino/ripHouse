package org.riphouse.filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.apache.http.auth.AuthenticationException;
import org.riphouse.annotations.Authorization;
import org.riphouse.exceptions.VechoException;
import org.riphouse.token.InfoToken;
import org.riphouse.token.TokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Authorization
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		try {
			TokenHandler tokenHandler = new TokenHandler();
			String token = tokenHandler.getStringToken(requestContext.getHeaderString(HttpHeaders.AUTHORIZATION));
			logger.info("token: {}", token);
			InfoToken infoToken = tokenHandler.getInfoToken(token);

			// TODO implementare ulteriore logica di validazione del token

		} catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
			throw new NotAuthorizedException(e.getMessage());
		} catch (VechoException e) {
			logger.error(e.getMessage(), e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}

}
