package org.riphouse.exceptions;

/**
 * Eccezione generica usare se si vuole catturare e rilanciare un'eccezione generica
 */
public class VechoException extends TrackedException {

	private static final long serialVersionUID = -6153898067411901772L;

	public VechoException() {
		super();
	}

	public VechoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VechoException(String message, Throwable cause) {
		super(message, cause);
	}

	public VechoException(String message) {
		super(message);
	}

	public VechoException(Throwable cause) {
		super(cause);
	}

	
}
