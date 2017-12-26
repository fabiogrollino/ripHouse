package org.riphouse.services.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.ArrayUtils;
import org.jboss.resteasy.spi.InternalServerErrorException;
import org.riphouse.dao.UtenteDAO;
import org.riphouse.dto.Utente;
import org.riphouse.exceptions.AuthenticationException;
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
	private UtenteDAO utenteDao;

	public LoginResponse login(LoginRequest loginRequest) {
		try {
			if (logger.isDebugEnabled()) logger.debug("REST - login: {}", loginRequest.getUsername());
			Utente utenteDTO = utenteDao.login(loginRequest.getUsername());
			if (utenteDTO == null) {
				throw new AuthenticationException("Username or passsword are incorrect!");
			}
			checks(utenteDTO, loginRequest);
			InfoToken infoToken = new InfoToken(utenteDTO.getUser(), 0, utenteDTO.getId(), utenteDTO.getAnagrafica());
			TokenHandler tokenHandler = new TokenHandler();
			String token = tokenHandler.generateToken(infoToken);
			if (logger.isInfoEnabled()) logger.info("User {} login success, new token: {}", utenteDTO.getUser(), token);
			return new LoginResponse(token, infoToken.getUser(), infoToken.getLevel(), infoToken.getIdUser(), infoToken.getIdAnagrafica());
		}  catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
			throw new ForbiddenException(e.getMessage());
		} catch (VechoException e) {
			logger.error(e.getMessage(), e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}
	
	private void checks(Utente utenteDTO, LoginRequest loginRequest) throws AuthenticationException, VechoException {
		
		//TODO gestire il numero massimo di tentativi nel breve periodo
		
		String userPassword = String.valueOf(ArrayUtils.toPrimitive(utenteDTO.getPassword()));
		String inputPassword = String.valueOf(ArrayUtils.toPrimitive(loginRequest.getPassword()));
		if (!utenteDTO.getUser().equals(loginRequest.getUsername()) || !userPassword.equals(inputPassword)) {
			handleLoginResult(utenteDTO, false);
			throw new AuthenticationException("Username or passsword are incorrect!");
		}
		handleLoginResult(utenteDTO, true);
	}

	private void handleLoginResult(Utente utenteDTO, boolean success) throws VechoException {
		//TODO aggiornare utente se necessario 
	}
}
