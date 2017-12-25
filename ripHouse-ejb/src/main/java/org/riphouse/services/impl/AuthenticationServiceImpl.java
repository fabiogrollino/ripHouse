package org.riphouse.services.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.auth.AuthenticationException;
import org.jboss.resteasy.spi.InternalServerErrorException;
import org.riphouse.dao.UserDao;
import org.riphouse.dto.UtenteDTO;
import org.riphouse.exceptions.VechoException;
import org.riphouse.requests.LoginRequest;
import org.riphouse.responses.LoginResponse;
import org.riphouse.services.AuthenticationService;
import org.riphouse.token.InfoToken;
import org.riphouse.token.TokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Local(AuthenticationService.class)
@Stateless
public class AuthenticationServiceImpl implements AuthenticationService {

	private Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	
	@Context
	UriInfo uriInfo;
	
	@EJB
	private UserDao userDao;

	public LoginResponse login(LoginRequest loginRequest) {
		try {
			if (logger.isDebugEnabled()) logger.debug("REST - login: {}", loginRequest.getUsername());
			UtenteDTO utenteDTO = userDao.login(loginRequest.getUsername());
			if (utenteDTO == null) {
				throw new AuthenticationException("Username or passsword are incorrect!");
			}
			checks(utenteDTO, loginRequest);
			InfoToken infoToken = new InfoToken(utenteDTO.getId(), utenteDTO.getUser(), utenteDTO.getAnagrafica());
			TokenHandler tokenHandler = new TokenHandler();
			String token = tokenHandler.generateToken(infoToken);
			logger.info("User {} login success, new token: {}", utenteDTO.getUser(), token);
			return new LoginResponse(token);
		}  catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
			throw new ForbiddenException(e.getMessage());
		} catch (VechoException e) {
			logger.error(e.getMessage(), e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}
	
	private void checks(UtenteDTO utenteDTO, LoginRequest loginRequest) throws AuthenticationException, VechoException {
		
		//TODO gestire il numero massimo di tentativi nel breve periodo
		
		String userPassword = String.valueOf(ArrayUtils.toPrimitive(utenteDTO.getPassword()));
		String inputPassword = String.valueOf(ArrayUtils.toPrimitive(loginRequest.getPassword()));
		if (!utenteDTO.getUser().equals(loginRequest.getUsername()) || !userPassword.equals(inputPassword)) {
			handleLoginResult(utenteDTO, false);
			throw new AuthenticationException("Username or passsword are incorrect!");
		}
		handleLoginResult(utenteDTO, true);
	}

	private void handleLoginResult(UtenteDTO utenteDTO, boolean success) throws VechoException {
		//TODO aggiornare utente se necessario 
	}
}