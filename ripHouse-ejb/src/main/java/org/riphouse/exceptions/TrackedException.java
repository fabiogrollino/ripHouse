package org.riphouse.exceptions;

/**
 * Eccezione padre da non rilanciare e catturare mai. Usata per rendere più
 * tracciabili le eccezioni figlie
 */
public class TrackedException extends Exception {

	private static final long serialVersionUID = -6153898067411901772L;

	private final String trackErrorID = new StringBuilder("TrackErrorID: ")
			.append(Long.toHexString(System.currentTimeMillis())).toString();

	public TrackedException() {
		super();
	}

	public TrackedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TrackedException(String message, Throwable cause) {
		super(message, cause);
	}

	public TrackedException(String message) {
		super(message);
	}

	public TrackedException(Throwable cause) {
		super(cause);
	}

	public String getTrackErrorID() {
		return trackErrorID;
	}

	@Override
	public String getMessage() {
		StringBuilder message = new StringBuilder(trackErrorID);
		message.append(" - ");
		message.append(super.getMessage());
		return message.toString();
	}

}
