package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;

public class AlfaNumericoRule implements Rule<String> {

	private MatchRule match = new MatchRule("([^A-Za-z0-9áéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ ])");

	@Override public String validate(String value) {
		return this.match.validate(value);
	}
}
