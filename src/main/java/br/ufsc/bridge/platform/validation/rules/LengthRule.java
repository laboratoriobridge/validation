package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class LengthRule implements Rule<String> {

	private final int length;

	public LengthRule(int length) {
		this.length = length;
	}

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && value.length() != this.length) {
			return "Deve possuir " + this.length + " caracteres";
		}
		return null;
	}
}
