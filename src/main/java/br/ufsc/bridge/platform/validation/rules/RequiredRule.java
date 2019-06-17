package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class RequiredRule implements Rule {

	@Override public String validate(Object value) {
		if (Util.isEmpty(value) || ((String) value).trim().length() == 0) {
			return "Preenchimento obrigatório";
		}
		return null;
	}

}
