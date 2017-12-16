package org.riphouse.handlers;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionHandler implements ExceptionMapper<ForbiddenException> {

	@Override
	public Response toResponse(ForbiddenException e) {
		return Response.status(Status.FORBIDDEN).entity(e.getMessage()).build();
	}

}
