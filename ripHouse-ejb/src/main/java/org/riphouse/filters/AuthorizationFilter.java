package org.riphouse.filters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.annotation.Priority;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.InternalServerErrorException;
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
@ManagedBean
public class AuthorizationFilter implements ContainerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		try {
			if (logger.isDebugEnabled()) logger.debug("filter - START");

			Authorization authorization = getAuthotizationAnnotation(requestContext);
			if (authorization == null) {
				return;
			}

			MultivaluedMap<String,String> requestHeaders = requestContext.getHeaders();
			TokenHandler tokenHandler = new TokenHandler();
			String token = tokenHandler.getStringToken(requestHeaders.getFirst(HttpHeaders.AUTHORIZATION));
			InfoToken infoToken = tokenHandler.getInfoToken(token);
			int userLevel = validate(infoToken);
			
			if (authorization.exact() && authorization.level() != userLevel) {
				throw new AuthorizationException("Not authorized to access at this service");
			} else if (authorization.level() > userLevel) {
				throw new AuthorizationException("Not authorized to access at this service");
			}
			
			if (authorization.verifyUser() && !infoToken.getIdUser().equals(getIdUser(requestContext))) {
				throw new AuthorizationException("Input's idUser and token's idUser aren't equal");
			}
			
			if (logger.isDebugEnabled()) logger.debug("filter - END");
			
		} catch (AuthenticationException e) {
			logger.error(e.getMessage());
			throw new ForbiddenException(e.getMessage());
		} catch (AuthorizationException e) {
			logger.error(e.getMessage());
			throw new NotAuthorizedException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new InternalServerErrorException(e.getMessage());
		}

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

	private int validate(InfoToken infoToken) throws AuthenticationException {
		if (infoToken == null) {
			throw new AuthenticationException("There aren't info in your token");
		}
		if (infoToken.getIdUser() == null) {
			throw new AuthenticationException("There isn't idUser in your token");
		}
		if (infoToken.getLevel() == null) {
			throw new AuthenticationException("There isn't level in your token");
		}
		return infoToken.getLevel();
	}

	private Authorization getAuthotizationAnnotation(ContainerRequestContext requestContext) {

		Authorization authorization = null;
		ResourceMethodInvoker rmi = (ResourceMethodInvoker) requestContext.getProperty(ResourceMethodInvoker.class.getName());
		Method method = rmi.getMethod();
		Class<?> clazz = rmi.getResourceClass();
		if (method != null) {
			authorization = method.getAnnotation(Authorization.class);
		}
		if (authorization == null && clazz != null) {
			authorization = clazz.getAnnotation(Authorization.class);
		}
		return authorization;

	}


}
