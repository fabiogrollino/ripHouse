package org.riphouse.services;

import java.io.UnsupportedEncodingException;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.spi.InternalServerErrorException;
import org.riphouse.requests.LoginRequest;
import org.riphouse.responses.LoginResponse;
import org.riphouse.token.InfoToken;
import org.riphouse.token.TokenHandler;

@Stateless
@Path("authentication")
public class AuthenticationService {

	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public LoginResponse login(LoginRequest loginRequest) {

		try {
			
			InfoToken infoToken = new InfoToken(); 
			//TODO decidere come valorizzare dopo aver effettuato il login
			
			
			TokenHandler tokenHandler = new TokenHandler();
			String token = tokenHandler.generateToken(infoToken);
			return new LoginResponse(token);
		} catch (UnsupportedEncodingException e) {
			//TODO loggare
			throw new InternalServerErrorException(e.getMessage());
		}
	}
}
