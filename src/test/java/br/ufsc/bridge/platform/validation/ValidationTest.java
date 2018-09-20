package br.ufsc.bridge.platform.validation;

import br.ufsc.bridge.platform.validation.engine.Rule;

public abstract class ValidationTest {

	public String validate(Object value, Rule rule) {
		return rule.validate(value);
	}

}
