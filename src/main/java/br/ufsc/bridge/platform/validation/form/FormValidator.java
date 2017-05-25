package br.ufsc.bridge.platform.validation.form;

import br.ufsc.bridge.platform.validation.form.errors.FormError;

@FunctionalInterface
public interface FormValidator<T> {

	void validate(T form, FormError errors);

}
