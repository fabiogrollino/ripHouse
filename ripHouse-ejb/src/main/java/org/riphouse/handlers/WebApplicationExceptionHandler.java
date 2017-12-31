package org.riphouse.handlers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class WebApplicationExceptionHandler implements ExceptionMapper<WebApplicationException> {

	private Logger logger = LoggerFactory.getLogger(WebApplicationExceptionHandler.class);
	
	@Override
	public Response toResponse(WebApplicationException e) {
		if (logger.isInfoEnabled()) logger.info(e.getMessage(), e);		
		return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
	}

}
