package br.ufsc.bridge.platform.validation.form.errors;

public interface ListError extends ValidationError {

	FormError itemError(int index);

	boolean isValid();

}
