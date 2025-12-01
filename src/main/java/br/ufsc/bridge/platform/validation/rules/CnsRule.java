package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class CnsRule implements Rule<String> {

	private final static String CNS1 = "^[1-2][0-9]{10}00[0-1][0-9]$";
	private final static String CNS2 = "^[57-9][0-9]{14}$";

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && !this.isValid(value)) {
			return "Campo inválido";
		}
		return null;
	}

	private boolean isValid(String cnsValue) {
		if (cnsValue.matches(CNS1) || cnsValue.matches(CNS2)) {
			return this.somaPonderada(cnsValue) % 11 == 0;
		}
		return false;
	}

	private int somaPonderada(String cnsValue) {
		int soma = 0;
		for (int i = 0; i < cnsValue.length(); i++) {
			soma += (cnsValue.codePointAt(i) - 48) * (15 - i);
		}
		return soma;
	}
}
