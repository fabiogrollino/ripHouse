package org.riphouse.application;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.riphouse.filters.AuthorizationFilter;
import org.riphouse.filters.RestCorsFilter;
import org.riphouse.handlers.ExceptionHandler;
import org.riphouse.handlers.ResponseHeaderHandler;
import org.riphouse.handlers.WebApplicationExceptionHandler;
import org.riphouse.services.impl.AuthenticationServiceImpl;

@ApplicationPath("")
public class ApplicationConfig extends Application {

	@Inject
	private AuthorizationFilter authorizationFilter;
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
		addRestResourceClasses(resources);
		addHandlerResourceClasses(resources);
		return resources;
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		singletons.add(authorizationFilter);
		singletons.add(new ResponseHeaderHandler());
		singletons.add(new RestCorsFilter());
		return singletons;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(AuthenticationServiceImpl.class);
	}
	
	private void addHandlerResourceClasses(Set<Class<?>> resources) {
		resources.add(WebApplicationExceptionHandler.class);
		resources.add(ExceptionHandler.class);

	}
	
}
