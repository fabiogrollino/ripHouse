package org.riphouse.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.riphouse.token.InfoToken;
import org.riphouse.token.TokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ResponseHeaderHandler implements ContainerResponseFilter {

	private Logger logger = LoggerFactory.getLogger(ResponseHeaderHandler.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		if (responseContext.getStatus() == Status.FORBIDDEN.getStatusCode()) {
			return;
		}
		
		MultivaluedMap<String,String> requestHeaders = requestContext.getHeaders();	
		if (requestHeaders.containsKey(HttpHeaders.AUTHORIZATION)) {
			try {
				TokenHandler tokenHandler = new TokenHandler();
				String token = tokenHandler.getStringToken(requestHeaders.getFirst(HttpHeaders.AUTHORIZATION)); 
				InfoToken infoToken = tokenHandler.getInfoToken(token);
				token = tokenHandler.generateToken(infoToken);
				MultivaluedMap<String,Object> responseHeaders = responseContext.getHeaders();
				responseHeaders.add(HttpHeaders.AUTHORIZATION, token);
			} catch (Exception e) {
				logger.error(e.getMessage(), e); 
				if (e instanceof WebApplicationException) {
					logger.info("WebApplicationException");
					WebApplicationException ex = (WebApplicationException) e;
					exceptionHandler(responseContext, ex, ex.getResponse().getStatus());
				} else {
					logger.info("Exception");
					exceptionHandler(responseContext, e, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
				}
			}
		}

	}
	
	private void exceptionHandler(ContainerResponseContext responseContext, Exception e, int statusCode) {
		responseContext.setStatus(statusCode);
		OutputStream outputStream = responseContext.getEntityStream();
		try {
			InputStream inputStream = IOUtils.toInputStream(e.getMessage() + " - ", StandardCharsets.UTF_8.name());
			IOUtils.copy(inputStream, outputStream);
		} catch (IOException ex) {
			logger.warn(ex.getMessage(), ex);
		}
	}

}
