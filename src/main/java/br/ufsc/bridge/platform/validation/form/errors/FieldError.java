package br.ufsc.bridge.platform.validation.form.errors;

public class FieldError implements ValidationError {

	private static final long serialVersionUID = 4518841165294372684L;
	private String mensagem;

	public FieldError(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return this.mensagem;
	}

}