package br.ufsc.bridge.platform.validation;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.engine.Validation;
import br.ufsc.bridge.platform.validation.form.errors.FieldError;

public abstract class ValidationTest {

	public FieldError validate(Object value, Rule rule) {
		return Validation.get().validate(value, rule);
	}
	
}
