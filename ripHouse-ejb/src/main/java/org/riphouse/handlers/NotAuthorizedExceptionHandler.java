package org.riphouse.handlers;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class NotAuthorizedExceptionHandler implements ExceptionMapper<NotAuthorizedException> {

	private Logger logger = LoggerFactory.getLogger(NotAuthorizedExceptionHandler.class);
	
	@Override
	public Response toResponse(NotAuthorizedException e) {
		logger.info(e.getMessage(), e);
		return Response.status(Status.UNAUTHORIZED).entity(e.getMessage()).build();
	}

}
