package org.riphouse.filters;

import java.io.IOException;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@PreMatching
@Provider
public class RestCorsFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (HttpMethod.OPTIONS.equals(requestContext.getMethod())) {
			requestContext.abortWith(Response.ok().build());
		}	
	}

}
