package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class MatchRule implements Rule<String> {

	private final String regex;

	public MatchRule(String regex) {
		this.regex = regex;
	}

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && !this.isValid(value)) {
			return "Um ou mais caracteres informados não são permitidos para esse campo";
		}
		return null;
	}

	public boolean isValid(String value) {
		return value.matches(this.regex);
	}
}
