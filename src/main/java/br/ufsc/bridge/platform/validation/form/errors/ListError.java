package br.ufsc.bridge.platform.validation.form.errors;

public interface ListError extends ValidationError {

	void error(String mensagem);

	FormError itemError(int index);

	boolean isValid();

}
