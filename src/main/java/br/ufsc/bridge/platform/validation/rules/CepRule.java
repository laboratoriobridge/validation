package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class CepRule implements Rule<String> {

	private MatchRule match = new MatchRule("^[0-9]{8}$");

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && !this.match.isValid(value)) {
			return "O CEP informado é inválido";
		}
		return null;
	}

}
