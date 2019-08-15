package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class EmailRule implements Rule<String> {

	private MatchRule match = new MatchRule("^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$");

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && !this.match.isValid(value)) {
			return "Campo inválido";
		}
		return null;
	}
}
