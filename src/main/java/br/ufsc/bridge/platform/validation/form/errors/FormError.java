package br.ufsc.bridge.platform.validation.form.errors;

public interface FormError extends ValidationError {

	boolean isValid();

	FormError formError(String form);

	ListError listError(String campo);

	void fieldError(String campo, String titulo, String mensagem);

	void fieldError(String campo, FieldError error);

	void cep(String campo);

	void cnpj(String campo);

	void cns(String campo);

	void cpf(String campo);

	void dataNasc(String campo);

	void dataMax(String campo);

	void duracao(String campo);

	void email(String campo);

	void hora(String campo);

	void length(String campo, Number length);

	void maxLength(String campo, Number maxLength);

	void maxRange(String campo, Number maxRange);

	void minLength(String campo, Number minLength);

	void minRange(String campo, Number minRange);

	void nome(String campo);

	void range(String campo, Number minRange, Number maxRange);

	void registroAnvisa(String campo);

	void required(String campo);

	void senha(String campo);

	void telefone(String campo);

	boolean fieldIsValid(String form);

}
