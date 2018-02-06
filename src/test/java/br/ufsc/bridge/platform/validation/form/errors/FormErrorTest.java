package br.ufsc.bridge.platform.validation.form.errors;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.metafy.Metafy;

public class FormErrorTest {

	private static final MFormErrorTest_Form meta = MFormErrorTest_Form.meta;

	@Test
	public void geral() {
		Form form = new Form();
		form.setRequired("1");

		FormError errors = new FormErrorImpl(form);

		errors.cep(meta.cep);
		errors.cnpj(meta.cnpj);
		errors.cns(meta.cns);
		errors.cpf(meta.cpf);
		errors.dataNasc(meta.dataNascimento);
		errors.duracao(meta.duracao);
		errors.email(meta.email);
		errors.hora(meta.hora);
		errors.dataMax(meta.maxDate);
		errors.nome(meta.nome);
		errors.required(meta.required);
		errors.telefone(meta.telefone);

		Assert.assertTrue(errors.isValid());
	}

	@Test
	public void invalido() {
		Form form = new Form();

		FormError errors = new FormErrorImpl(form);

		errors.cep(meta.cep);
		errors.cnpj(meta.cnpj);
		errors.cns(meta.cns);
		errors.cpf(meta.cpf);
		errors.dataNasc(meta.dataNascimento);
		errors.duracao(meta.duracao);
		errors.email(meta.email);
		errors.hora(meta.hora);
		errors.dataMax(meta.maxDate);
		errors.nome(meta.nome);
		errors.required(meta.required);
		errors.telefone(meta.telefone);

		Assert.assertFalse(errors.isValid());
	}

	@Metafy
	public static class Form {
		private String cep;
		private String cnpj;
		private String cns;
		private String cpf;
		private LocalDate dataNascimento;
		private String duracao;
		private String email;
		private String hora;
		private LocalDate maxDate;
		private String nome;
		private String required;
		private String telefone;

		public String getCep() {
			return this.cep;
		}

		public void setCep(String cep) {
			this.cep = cep;
		}

		public String getCnpj() {
			return this.cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public String getCns() {
			return this.cns;
		}

		public void setCns(String cns) {
			this.cns = cns;
		}

		public String getCpf() {
			return this.cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public LocalDate getDataNascimento() {
			return this.dataNascimento;
		}

		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}

		public String getDuracao() {
			return this.duracao;
		}

		public void setDuracao(String duracao) {
			this.duracao = duracao;
		}

		public String getEmail() {
			return this.email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getHora() {
			return this.hora;
		}

		public void setHora(String hora) {
			this.hora = hora;
		}

		public LocalDate getMaxDate() {
			return this.maxDate;
		}

		public void setMaxDate(LocalDate maxDate) {
			this.maxDate = maxDate;
		}

		public String getNome() {
			return this.nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getRequired() {
			return this.required;
		}

		public void setRequired(String required) {
			this.required = required;
		}

		public String getTelefone() {
			return this.telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

	}
}
