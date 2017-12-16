package org.riphouse.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		//TODO loggare
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	}

}
