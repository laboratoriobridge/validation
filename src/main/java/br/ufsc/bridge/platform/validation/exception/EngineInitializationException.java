package br.ufsc.bridge.platform.validation.exception;

public class EngineInitializationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1310942832039396307L;

	public EngineInitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EngineInitializationException(String message) {
		super(message);
	}

}
