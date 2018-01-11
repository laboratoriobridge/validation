package br.ufsc.bridge.platform.validation;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.engine.Validation;
import br.ufsc.bridge.platform.validation.form.errors.FieldError;

public abstract class ValidationTest {

	public String validate(Object value, Rule rule) {
		FieldError fieldError = Validation.get().validate(value, rule);
		if (fieldError == null) {
			return null;
		} else {
			return fieldError.getMensagem();
		}
	}

}
