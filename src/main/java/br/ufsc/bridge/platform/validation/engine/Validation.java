package br.ufsc.bridge.platform.validation.engine;

import br.ufsc.bridge.platform.validation.form.errors.FieldError;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

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
		ScriptObjectMirror result = (ScriptObjectMirror) this.engine.serverValidate(value, rule);
		if (result != null) {
			return new FieldError((String) result.get("titulo"), (String) result.get("mensagem"));
		}
		return null;
	}

}
