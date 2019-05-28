package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class MaxLengthRule implements Rule<String> {

	private final int maxLength;

	public MaxLengthRule(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public String validate(String value) {
		if (!Util.isEmpty(value) && value.length() > this.maxLength) {
			return "Deve possuir menos que " + this.maxLength + " caracteres";
		}
		return null;
	}
}
