package org.riphouse.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.riphouse.requests.LoginRequest;
import org.riphouse.responses.LoginResponse;

@Path("authentication")
public interface AuthenticationService {

	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public LoginResponse login(LoginRequest loginRequest);
}
