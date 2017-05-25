package br.ufsc.bridge.platform.validation.form;

import java.util.List;

import br.ufsc.bridge.platform.validation.form.errors.FormError;
import br.ufsc.bridge.platform.validation.form.errors.ListError;

public abstract class FormListValidator<T> {

	public final void validate(List<T> form, ListError errors) {
		for (int i = 0; i < form.size(); i++) {
			T item = form.get(i);

			this.validateItem(item, errors.itemError(i));
		}
	}

	protected abstract void validateItem(T item, FormError errors);

}
