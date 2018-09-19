package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class EmailRule implements Rule<String> {

	private MatchRule match = new MatchRule("^[_a-z0-9-\\+]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$");

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && !this.match.isValid(value)) {
			return "E-mail informado é inválido";
		}
		return null;
	}
}
