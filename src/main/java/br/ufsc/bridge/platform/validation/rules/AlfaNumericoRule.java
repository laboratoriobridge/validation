package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;

public class AlfaNumericoRule implements Rule<String> {

	private MatchRule match = new MatchRule("^([a-zA-Z0-9áéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ'\\s])+$");

	@Override
	public String validate(String value) {
		return this.match.validate(value);
	}
}
