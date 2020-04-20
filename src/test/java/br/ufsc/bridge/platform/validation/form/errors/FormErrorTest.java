package br.ufsc.bridge.platform.validation.form.errors;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.metafy.Metafy;
import br.ufsc.bridge.platform.validation.rules.Rules;

public class FormErrorTest {

	private static final MFormErrorTest_Form meta = MFormErrorTest_Form.meta;
	private static final MFormErrorTest_SubForm submeta = MFormErrorTest_SubForm.meta;

	@Test
	public void geral() {
		Form form = new Form();
		form.setRequired("1");

		SubForm subform = new SubForm();
		subform.setNome("José");
		form.setList(Collections.singletonList(subform));

		FormError<Form> errors = new FormErrorImpl<>(form);

		errors
				.check(meta.cep, Rules.cep)
				.check(meta.cnpj, Rules.cnpj)
				.check(meta.cns, Rules.cns)
				.check(meta.cpf, Rules.cpf)
				.check(meta.email, Rules.email)
				.check(meta.hora, Rules.hour)
				.check(meta.maxDate, Rules.beforeToday);
		errors.check(meta.required, Rules.required);
		errors.check(meta.telefone, Rules.phone)
				.forEach(meta.list, (item, itemError) ->
						itemError.check(submeta.nome, Rules.required)
				);

		Assert.assertTrue(errors.isValid());
	}

	@Test
	public void invalido() {
		Form form = new Form();
		form.setList(Collections.singletonList(new SubForm()));

		FormError<Form> errors = new FormErrorImpl<>(form);

		errors
				.check(meta.cep, Rules.cep)
				.check(meta.cnpj, Rules.cnpj)
				.check(meta.cns, Rules.cns)
				.check(meta.cpf, Rules.cpf)
				.check(meta.email, Rules.email)
				.check(meta.hora, Rules.hour)
				.check(meta.maxDate, Rules.beforeToday);
		errors.check(meta.required, Rules.required);
		errors.check(meta.telefone, Rules.phone)
				.forEach(meta.list, (item, itemError) ->
						itemError.check(submeta.nome, Rules.required)
				);

		Assert.assertFalse(errors.isValid(meta.list));
		Assert.assertFalse(errors.isValid());
	}

	@Test
	public void desconsiderarMensagensNulas() {
		Form form = new Form();

		FormError errors = new FormErrorImpl<>(form);

		errors.fieldError(meta.cep, null);

		Assert.assertTrue(errors.isValid());
	}

	@Test
	public void formRootError() {
		Form form = new Form();

		FormError errors = new FormErrorImpl<>(form);

		errors.error("Objeto inválido");

		Assert.assertFalse(errors.isValid());
		Assert.assertEquals("Objeto inválido", errors.getErrors());
	}

	@Test
	public void subFormRootError() {
		Form form = new Form();

		FormError errors = new FormErrorImpl<>(form);

		errors.formError(meta.sub()).error("Objeto inválido");

		errors.error("Objeto inválido");

		Assert.assertFalse(errors.isValid());
		Assert.assertEquals("Objeto inválido", errors.getErrors());

		errors.error(null);

		Assert.assertFalse(errors.isValid());
		Assert.assertEquals("Objeto inválido", ((Map) errors.getErrors()).get(meta.sub().getAlias()));
	}

	@Test
	public void shouldHaveErrorsOnTheSubForm() {
		Form form = new Form();

		FormErrorImpl<Form> errors = new FormErrorImpl<>(form);

		FormError<SubForm> subErrors = errors.formError(meta.sub());

		subErrors.fieldError(MFormErrorTest_SubForm.meta.nome, "invalid name");

		Assert.assertFalse(errors.isValid());
	}

	@Test
	public void shouldAcceptExternalErrorsOnTheSubForm() {
		Form form = new Form();

		FormErrorImpl<Form> errors = new FormErrorImpl<>(form);

		FormErrorImpl<SubForm> subErrors = new FormErrorImpl<>(form.getSub());

		subErrors.fieldError(MFormErrorTest_SubForm.meta.nome, "invalid name");

		errors.formError(meta.sub(), subErrors);

		Assert.assertFalse(errors.isValid());
	}

	@Test
	public void listRootError() {
		Form form = new Form();

		FormError errors = new FormErrorImpl<>(form);

		errors.listError(meta.list).error("Objeto inválido");

		Assert.assertFalse(errors.isValid());
		Assert.assertEquals("Objeto inválido", ((Map) errors.getErrors()).get(meta.list.getAlias()));
	}

	@Test
	public void nullForm() {
		FormError<Form> errors = new FormErrorImpl<>(null);

		errors.check(meta.nome, Rules.required);

		Assert.assertFalse(errors.isValid());
	}

	@Test
	public void multipleRules() {
		FormError<Form> errors = new FormErrorImpl<>(null);

		errors
				.check(meta.cpf, Rules.required)
				.check(meta.cpf, Rules.cpf);

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
		private List<SubForm> list;
		private SubForm sub;

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

		public List<SubForm> getList() {
			return this.list;
		}

		public void setList(List<SubForm> list) {
			this.list = list;
		}

		public SubForm getSub() {
			return this.sub;
		}

		public void setSub(SubForm sub) {
			this.sub = sub;
		}
	}

	@Metafy
	public static class SubForm {
		private String nome;

		public String getNome() {
			return this.nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
	}
}
