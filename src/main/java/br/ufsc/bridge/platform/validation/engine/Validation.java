package br.ufsc.bridge.platform.validation.engine;

import br.ufsc.bridge.platform.validation.form.errors.FieldError;

public class Validation {

	private static Validation instance;
	private final Engine engine = new Engine();

	private Validation() {
		// nada a fazer
	}

	public static synchronized Validation get() {
		if (instance == null) {
			instance = new Validation();
		}

		return instance;
	}

	public FieldError validate(Object value, Rule rule) {
		String errorMessage = (String) this.engine.serverValidate(value, rule);
		if (errorMessage != null) {
			return new FieldError(errorMessage);
		}
		return null;
	}

}
