package br.ufsc.bridge.platform.validation.form.errors;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import br.ufsc.bridge.metafy.MetaField;
import br.ufsc.bridge.metafy.MetaList;
import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.engine.Validation;
import br.ufsc.bridge.platform.validation.rules.Rules;
import br.ufsc.bridge.platform.validation.util.Reflections;

public class FormErrorImpl extends HashMap<String, ValidationError> implements FormError {

	private static final long serialVersionUID = -6446758048046334551L;

	private transient Object target;

	public FormErrorImpl(Object target) {
		super();
		this.target = target;
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void fieldError(String campo, String titulo, String mensagem) {
		this.fieldError(campo, new FieldError(titulo, mensagem));
	}

	@Override
	public void fieldError(MetaField<?> field, String titulo, String mensagem) {
		this.fieldError(field.getAlias(), new FieldError(titulo, mensagem));
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void fieldError(String campo, FieldError error) {
		this.put(campo, error);
	}

	@Override
	public void fieldError(MetaField<?> field, FieldError error) {
		this.fieldError(field.getAlias(), error);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public FormError formError(String form) {
		FormError error = (FormError) this.get(form);
		if (error == null) {
			error = new FormErrorImpl(Reflections.getValue(this.target, form));
			this.put(form, error);
		}
		return error;
	}

	@Override
	public FormError formError(MetaField<?> field) {
		return this.formError(field.getAlias());
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public ListError listError(String list) {
		ListError error = (ListError) this.get(list);
		if (error == null) {
			error = new ListErrorImpl((List<?>) Reflections.getValue(this.target, list));
			this.put(list, error);
		}
		return error;
	}

	@Override
	public ListError listError(MetaList<?> field) {
		return this.listError(field.getAlias());
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public boolean fieldIsValid(String form) {
		boolean valid = true;
		ValidationError error = this.get(form);
		if (error != null && error instanceof FieldError) {
			valid = false;
		} else if (error != null && error instanceof FormError) {
			valid = ((FormErrorImpl) error).isValid();
		}
		return valid;
	}

	@Override
	public boolean fieldIsValid(MetaField<?> field) {
		return this.fieldIsValid(field.getAlias());
	}

	@Override
	public boolean isValid() {
		boolean valid = true;

		Iterator<ValidationError> iterator = this.values().iterator();
		while (iterator.hasNext()) {
			ValidationError validationError = iterator.next();
			if (validationError instanceof FormErrorImpl) {
				boolean childFormValid = ((FormErrorImpl) validationError).isValid();
				valid = valid && childFormValid;
				if (childFormValid) {
					iterator.remove();
				}
			} else if (validationError instanceof ListErrorImpl) {
				boolean childFormValid = ((ListErrorImpl) validationError).isValid();
				valid = valid && childFormValid;
				if (childFormValid) {
					iterator.remove();
				}
			} else {
				valid = false;
			}
		}

		return valid;
	}

	private <F> void runRule(MetaField<F> field, Rule rule) {
		this.runRule(field.getAlias(), rule);
	}

	private void runRule(String campo, Rule rule) {
		if (this.fieldIsValid(campo)) {
			FieldError result;
			if (this.target != null) {
				result = Validation.get().validate(Reflections.getValue(this.target, campo), rule);
			} else {
				result = Validation.get().validate(null, rule);
			}
			if (result != null) {
				this.fieldError(campo, result);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.target == null ? 0 : this.target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		FormErrorImpl other = (FormErrorImpl) obj;
		if (this.target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!this.target.equals(other.target)) {
			return false;
		}
		return true;
	}

	// Regras
	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void alfaNumerico(String campo) {
		this.runRule(campo, Rules.alfaNumerico);
	}

	@Override
	public void alfaNumerico(MetaField<String> field) {
		this.runRule(field.getAlias(), Rules.alfaNumerico);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void cep(String campo) {
		this.runRule(campo, Rules.cep);
	}

	@Override
	public void cep(MetaField<String> field) {
		this.runRule(field.getAlias(), Rules.cep);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void cpf(String campo) {
		this.runRule(campo, Rules.cpf);
	}

	@Override
	public void cpf(MetaField<String> field) {
		this.runRule(field, Rules.cpf);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void cnpj(String campo) {
		this.runRule(campo, Rules.cnpj);
	}

	@Override
	public void cnpj(MetaField<String> field) {
		this.runRule(field, Rules.cnpj);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void cns(String campo) {
		this.runRule(campo, Rules.cns);
	}

	@Override
	public void cns(MetaField<String> field) {
		this.runRule(field, Rules.cns);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void dataNasc(String campo) {
		this.runRule(campo, Rules.dataNascimento);
	}

	@Override
	public void dataNasc(MetaField<LocalDate> field) {
		this.runRule(field, Rules.dataNascimento);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void dataMax(String campo) {
		this.runRule(campo, Rules.maxDate);
	}

	@Override
	public void dataMax(MetaField<LocalDate> field) {
		this.runRule(field, Rules.maxDate);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void duracao(String campo) {
		this.runRule(campo, Rules.duracao);
	}

	@Override
	public void duracao(MetaField<String> field) {
		this.runRule(field, Rules.duracao);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void email(String campo) {
		this.runRule(campo, Rules.email);
	}

	@Override
	public void email(MetaField<String> field) {
		this.runRule(field, Rules.email);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void hora(String campo) {
		this.runRule(campo, Rules.hora);
	}

	@Override
	public void hora(MetaField<String> field) {
		this.runRule(field, Rules.hora);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void length(String campo, Number length) {
		this.runRule(campo, Rules.length(length));
	}

	@Override
	public void length(MetaField<String> field, Number length) {
		this.runRule(field, Rules.length(length));
	}

	@Override
	public void match(MetaField<String> field, String regex) {
		this.runRule(field, Rules.match(regex));
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void maxLength(String campo, Number maxLength) {
		this.runRule(campo, Rules.maxLength(maxLength));
	}

	@Override
	public void maxLength(MetaField<String> field, Number maxLength) {
		this.runRule(field, Rules.maxLength(maxLength));
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void maxRange(String campo, Number maxRange) {
		this.runRule(campo, Rules.maxRange(maxRange));
	}

	@Override
	public void maxRange(MetaField<Number> field, Number maxRange) {
		this.runRule(field, Rules.maxRange(maxRange));
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void minLength(String campo, Number minLength) {
		this.runRule(campo, Rules.minLength(minLength));
	}

	@Override
	public void minLength(MetaField<String> field, Number minLength) {
		this.runRule(field, Rules.minLength(minLength));
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void minRange(String campo, Number minRange) {
		this.runRule(campo, Rules.minRange(minRange));
	}

	@Override
	public void minRange(MetaField<Number> field, Number minRange) {
		this.runRule(field, Rules.minRange(minRange));
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void nome(String campo) {
		this.runRule(campo, Rules.nome);
	}

	@Override
	public void nome(MetaField<String> field) {
		this.runRule(field, Rules.nome);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void range(String campo, Number minRange, Number maxRange) {
		this.runRule(campo, Rules.range(minRange, maxRange));
	}

	@Override
	public void range(MetaField<Number> field, Number minRange, Number maxRange) {
		this.runRule(field, Rules.range(minRange, maxRange));
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void registroAnvisa(String campo) {
		this.runRule(campo, Rules.registroAnvisa);
	}

	@Override
	public void registroAnvisa(MetaField<String> field) {
		this.runRule(field, Rules.registroAnvisa);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void required(String campo) {
		this.runRule(campo, Rules.required);
	}

	@Override
	public void required(MetaField<?> field) {
		this.runRule(field, Rules.required);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void senha(String campo) {
		this.runRule(campo, Rules.senha);
	}

	@Override
	public void senha(MetaField<String> field) {
		this.runRule(field, Rules.senha);
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public void telefone(String campo) {
		this.runRule(campo, Rules.telefone);
	}

	@Override
	public void telefone(MetaField<String> field) {
		this.runRule(field, Rules.telefone);
	}

}