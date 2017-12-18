package org.riphouse.exceptions;

public class AuthorizationException extends TrackedException {

	private static final long serialVersionUID = -4467074016988072659L;

	public AuthorizationException() {
		super();
	}

	public AuthorizationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(Throwable cause) {
		super(cause);
	}

	
}
