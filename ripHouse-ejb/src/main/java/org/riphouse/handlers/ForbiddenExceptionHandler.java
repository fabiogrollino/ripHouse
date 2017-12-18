package org.riphouse.handlers;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ForbiddenExceptionHandler implements ExceptionMapper<ForbiddenException> {

	private Logger logger = LoggerFactory.getLogger(ForbiddenExceptionHandler.class);
	
	@Override
	public Response toResponse(ForbiddenException e) {
		logger.info(e.getMessage(), e);
		return Response.status(Status.FORBIDDEN).entity(e.getMessage()).build();
	}

}
