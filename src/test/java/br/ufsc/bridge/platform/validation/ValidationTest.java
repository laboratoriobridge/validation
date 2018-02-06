package br.ufsc.bridge.platform.validation;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.engine.Validation;

public abstract class ValidationTest {

	public String validate(Object value, Rule rule) {
		return Validation.get().validate(value, rule);
	}

}
