package org.riphouse.handlers;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class InternalServerErrorExceptionHandler implements ExceptionMapper<InternalServerErrorException> {

	private Logger logger = LoggerFactory.getLogger(InternalServerErrorExceptionHandler.class);
	
	@Override
	public Response toResponse(InternalServerErrorException e) {
		logger.info(e.getMessage(), e);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	}

}
