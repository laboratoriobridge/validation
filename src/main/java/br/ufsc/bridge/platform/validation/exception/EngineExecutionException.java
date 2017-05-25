package br.ufsc.bridge.platform.validation.exception;

public class EngineExecutionException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 5318612441360618617L;

	public EngineExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public EngineExecutionException(String message) {
		super(message);
	}

}
