package org.riphouse.handlers;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorExceptionHandler implements ExceptionMapper<InternalServerErrorException> {

	@Override
	public Response toResponse(InternalServerErrorException e) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	}

}
