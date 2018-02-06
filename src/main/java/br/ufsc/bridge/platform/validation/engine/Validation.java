package br.ufsc.bridge.platform.validation.engine;

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

	public String validate(Object value, Rule rule) {
		return (String) this.engine.serverValidate(value, rule);
	}

}
