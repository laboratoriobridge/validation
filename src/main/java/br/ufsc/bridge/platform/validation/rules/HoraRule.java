package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class HoraRule implements Rule<String> {

	private MatchRule match = new MatchRule("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && !this.match.isValid(value)) {
			return "Horário informado é inválido";
		}
		return null;
	}

}
