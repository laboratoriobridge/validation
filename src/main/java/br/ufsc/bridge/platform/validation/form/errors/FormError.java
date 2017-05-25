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

	void length(String campo, int length);
	
	void maxLength(String campo, int maxLength);
	
	void maxRange(String campo, int maxRange);
	
	void minLength(String campo, int minLength);
	
	void minRange(String campo, int minRange);
	
	void nome(String campo);

	void range(String campo, int minRange, int maxRange);
	
	void registroAnvisa(String campo);

	void required(String campo);

	void telefone(String campo);

	boolean fieldIsValid(String form);

}
