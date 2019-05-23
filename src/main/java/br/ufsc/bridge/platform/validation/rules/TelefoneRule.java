package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class TelefoneRule implements Rule<String> {

	private MatchRule match = new MatchRule("^\\d{2}9?\\d{8}$");

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && (this.hasTodosNumerosIguais(value) || !this.match.isValid(value))) {
			return "Campo inválido";
		}
		return null;
	}

	private boolean hasTodosNumerosIguais(String value) {
		CharSequence telefoneSemDDD = value.subSequence(2, value.length());
		for (int i = 0; i < telefoneSemDDD.length(); i++) {
			if (telefoneSemDDD.charAt(0) != telefoneSemDDD.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}
