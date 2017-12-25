package org.riphouse.filters;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Priority;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.interception.PostMatchContainerRequestContext;
import org.riphouse.annotations.Authorization;
import org.riphouse.exceptions.AuthenticationException;
import org.riphouse.exceptions.AuthorizationException;
import org.riphouse.token.InfoToken;
import org.riphouse.token.TokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

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
			validate(infoToken);

			Long idUserService = getIdUser(requestContext);

			if (idUserService != null && idUserService.longValue() != infoToken.getIdUser().longValue()) {
				throw new AuthorizationException("idUser in token not equals idUser in request");
			}

			Integer serviceLevel = null;

			if (requestContext instanceof PostMatchContainerRequestContext) {
				PostMatchContainerRequestContext pmcRequestContext =  (PostMatchContainerRequestContext) requestContext;
				ResourceMethodInvoker rmi = pmcRequestContext.getResourceMethod();
				Integer levelMethod = getServiceLevel(rmi.getMethod());
				Integer levelClass = getServiceLevel(rmi.getClass());
				serviceLevel = levelMethod != null ? levelMethod : levelClass;
			}

			if (serviceLevel == null || serviceLevel.intValue() == 0) {
				return;
			}
			if (infoToken.getLevel() == null || infoToken.getLevel().intValue() < serviceLevel.intValue()) {
				throw new AuthorizationException("Level access incorrect");
			}

		} catch (AuthenticationException e) {
			logger.error(e.getMessage(), e);
			throw new ForbiddenException(e.getMessage());
		} catch (AuthorizationException e) {
			logger.error(e.getMessage(), e);
			throw new NotAuthorizedException(e.getMessage());
		}
	}


	private Integer getServiceLevel(AnnotatedElement element) {
		Authorization authorization = null;
		if (element != null) {
			authorization = element.getAnnotation(Authorization.class);
		}
		if (authorization != null) {
			return Integer.valueOf(authorization.level());
		}
		return null;
	}

	private Long getIdUser(ContainerRequestContext requestContext) throws IOException {
		if (HttpMethod.GET.equals(requestContext.getMethod())) {
			return getIdUserFromGET(requestContext);
		} else if (HttpMethod.POST.equals(requestContext.getMethod())) {
			return getIdUserFromPOST(requestContext);
		} else {
			throw new UnsupportedOperationException("Method " + requestContext.getMethod() + " not supported for this operation");
		}
	}

	private Long getIdUserFromPOST(ContainerRequestContext requestContext) throws IOException {

		if(logger.isDebugEnabled()) logger.debug("getIdUserFromPOST");

		LineIterator iterator = IOUtils.lineIterator(requestContext.getEntityStream(), StandardCharsets.UTF_8.name());
		StringBuilder sb = new StringBuilder();
		while (iterator.hasNext()) {
			sb.append(iterator.nextLine());
		}
		requestContext.setEntityStream(IOUtils.toInputStream(sb.toString(), StandardCharsets.UTF_8.name()));

		Map<String, Object> payload = new HashMap<>();
		if (sb != null && sb.length() != 0) {
			Type type = payload.getClass();
			payload = new Gson().fromJson(sb.toString(), type);
		}
		Object idUserObj = payload.get("idUser"); 
		if (idUserObj != null) {
			long idUser = Double.valueOf(idUserObj.toString()).longValue();
			return new Long(idUser);
		}
		return null;
	}

	private Long getIdUserFromGET(ContainerRequestContext requestContext) {

		if(logger.isDebugEnabled()) logger.debug("getIdUserFromGET");

		MultivaluedMap<String,String> queryParams = requestContext.getUriInfo().getQueryParameters();		
		String idUserStr = queryParams.getFirst("idUser");	
		if (idUserStr != null) {
			long id = Double.valueOf(idUserStr).longValue();
			return new Long(id);
		}	
		return null;
	}

	private void validate(InfoToken infoToken) throws AuthenticationException {
		if (infoToken == null) {
			throw new AuthenticationException("There aren't info in your token");
		}
		if (infoToken.getIdUser() == null) {
			throw new AuthenticationException("There isn't idUser in your token");
		}
		if (infoToken.getLevel() == null) {
			throw new AuthenticationException("There isn't level in your token");
		}
	}
}
