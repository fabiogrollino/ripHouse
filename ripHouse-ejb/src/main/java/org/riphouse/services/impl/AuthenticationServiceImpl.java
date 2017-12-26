package org.riphouse.services.impl;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.ArrayUtils;
import org.riphouse.config.LoaderConfig;
import org.riphouse.config.RipHouseConfig;
import org.riphouse.dao.UtenteDAO;
import org.riphouse.dto.Utente;
import org.riphouse.exceptions.AuthenticationException;
import org.riphouse.exceptions.AuthorizationException;
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
	private RipHouseConfig config = LoaderConfig.getConfig();
	
	@Context
	UriInfo uriInfo;
	
	@EJB
	private UtenteDAO utenteDao;

	public LoginResponse login(LoginRequest loginRequest) {
		try {
			if (logger.isDebugEnabled()) logger.debug("REST - login: {}", loginRequest.getUsername());
			Utente utente = utenteDao.login(loginRequest.getUsername());
			if (utente == null) {
				throw new AuthenticationException("Username or passsword are incorrect!");
			}
			checks(utente, loginRequest);
			InfoToken infoToken = new InfoToken(utente.getUser(), utente.getLivello(), utente.getId(), utente.getAnagrafica());
			TokenHandler tokenHandler = new TokenHandler();
			String token = tokenHandler.generateToken(infoToken);
			if (logger.isInfoEnabled()) logger.info("User {} login success, new token: {}", utente.getUser(), token);
			return new LoginResponse(token, infoToken.getUser(), infoToken.getLevel(), infoToken.getIdUser(), infoToken.getIdAnagrafica());
		}  catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
			throw new ForbiddenException(e.getMessage());
		}  catch (AuthorizationException e) {
			logger.error(e.getMessage(), e);
			throw new NotAuthorizedException(e.getMessage());
		}
	}
	
	private void checks(Utente utente, LoginRequest loginRequest) throws AuthenticationException, AuthorizationException {
		checksPossibilities(utente);
		String userPassword = String.valueOf(ArrayUtils.toPrimitive(utente.getPassword()));
		String inputPassword = String.valueOf(ArrayUtils.toPrimitive(loginRequest.getPassword()));
		if (!utente.getUser().equals(loginRequest.getUsername()) || !userPassword.equals(inputPassword)) {
			handleLoginResult(utente, false);
			throw new AuthenticationException("Username or passsword are incorrect!");
		}
		handleLoginResult(utente, true);
	}

	private void handleLoginResult(Utente utente, boolean success) {
		if (success) {
			utente.setUltimologin(new Date());
			utente.setCountloginfailed(Integer.valueOf(0));
			utente.setFirstLoginFailed(null);
		} else {
			Integer fails = utente.getCountloginfailed();
			fails++;
			utente.setCountloginfailed(fails);
			if (utente.getFirstLoginFailed() == null) {
				utente.setFirstLoginFailed(new Date());
			}
		}
		utenteDao.update(utente);
	}
	
	private void checksPossibilities(Utente utente) throws AuthenticationException, AuthorizationException {
		
		if (utente.getUtentebloccato() != null && utente.getUtentebloccato()) {
			throw new AuthorizationException("Your profire is blocked by admin");
		}	
		Date firstLoginFailed = utente.getFirstLoginFailed();
		Integer countLoginFailed = utente.getCountloginfailed();
		if (firstLoginFailed == null || countLoginFailed < config.getMaxLoginAttempts()) {
			return;
		}
		long timeForRetry = firstLoginFailed.getTime() + config.getTimeForRetry();
		if (countLoginFailed >= config.getMaxLoginAttempts() && timeForRetry <= System.currentTimeMillis()) {
			return;
		}	
		throw new AuthenticationException("Too much failed attempts, please retry later");
		
	}
}
