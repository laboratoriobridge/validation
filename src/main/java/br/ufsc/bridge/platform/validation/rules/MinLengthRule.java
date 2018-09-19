package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class MinLengthRule implements Rule<String> {

	private final int minLength;

	public MinLengthRule(int minLength) {
		this.minLength = minLength;
	}

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && value.length() > this.minLength) {
			return "Campo deve possuir ao menos " + this.minLength + " caracteres";
		}
		return null;
	}

}
