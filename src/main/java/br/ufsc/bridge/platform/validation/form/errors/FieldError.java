package br.ufsc.bridge.platform.validation.form.errors;

public class FieldError implements ValidationError {

	private static final long serialVersionUID = 4518841165294372684L;
	private String titulo;
	private String mensagem;

	public FieldError(String titulo, String mensagem) {
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public String getMensagem() {
		return this.mensagem;
	}

}