package org.riphouse.handlers;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BadRequestExceptionHandler implements ExceptionMapper<BadRequestException> {

	private Logger logger = LoggerFactory.getLogger(BadRequestExceptionHandler.class);
	
	@Override
	public Response toResponse(BadRequestException e) {
		logger.info(e.getMessage(), e);
		return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
	}

}
