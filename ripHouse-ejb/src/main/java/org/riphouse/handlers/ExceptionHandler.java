package org.riphouse.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

	private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@Override
	public Response toResponse(Exception e) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getMessage(e)).build();
	}

	private String getMessage(Exception e) {
		StringBuilder message = new StringBuilder("TrackErrorID: ");
		message.append(Long.toHexString(System.currentTimeMillis()));
		if (e.getMessage() != null) {
			message.append(" - ");
			message.append(e.getMessage());
		}
		logger.error("{}", message, e);
		return message.toString();
	}

}
