package br.ufsc.bridge.platform.validation.form.errors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.joda.time.LocalDate;

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
	public void range(String campo, Number minRange, Number maxRange) {
		this.runRule(campo, Rules.range(minRange, maxRange));
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

	@Override
	public FormError formError(MetaField<?> field) {
		FormError error = (FormError) this.get(field.getAlias());
		if (error == null) {
			error = new FormErrorImpl(Reflections.getValue(this.target, field.getAlias()));
			this.put(field.getAlias(), error);
		}
		return error;
	}

	@Override
	public ListError listError(MetaList<?> field) {
		ListError error = (ListError) this.get(field.getAlias());
		if (error == null) {
			error = new ListErrorImpl((List<?>) Reflections.getValue(this.target, field.getAlias()));
			this.put(field.getAlias(), error);
		}
		return error;
	}

	@Override
	public boolean fieldIsValid(MetaField<?> field) {
		return this.fieldIsValid(field.getAlias());
	}

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	@Override
	public boolean fieldIsValid(String campo) {
		boolean valid = true;
		ValidationError error = this.get(campo);
		if (error != null && error instanceof FieldError) {
			valid = false;
		} else if (error != null && error instanceof FormError) {
			valid = ((FormErrorImpl) error).isValid();
		}
		return valid;
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
	@Override
	public void alfaNumerico(MetaField<String> field) {
		this.runRule(field, Rules.alfaNumerico);
	}

	@Override
	public void cep(MetaField<String> field) {
		this.runRule(field, Rules.cep);
	}

	@Override
	public void cpf(MetaField<String> field) {
		this.runRule(field, Rules.cpf);
	}

	@Override
	public void cnpj(MetaField<String> field) {
		this.runRule(field, Rules.cnpj);
	}

	@Override
	public void cns(MetaField<String> field) {
		this.runRule(field, Rules.cns);
	}

	@Override
	public void dataNasc(MetaField<LocalDate> field) {
		this.runRule(field, Rules.dataNascimento);
	}

	@Override
	public void dataMax(MetaField<LocalDate> field) {
		this.runRule(field, Rules.maxDate);
	}

	@Override
	public void duracao(MetaField<String> field) {
		this.runRule(field, Rules.duracao);
	}

	@Override
	public void email(MetaField<String> field) {
		this.runRule(field, Rules.email);
	}

	@Override
	public void hora(MetaField<String> field) {
		this.runRule(field, Rules.hora);
	}

	@Override
	public void length(MetaField<String> field, Number length) {
		this.runRule(field, Rules.length(length));
	}

	@Override
	public void match(MetaField<String> field, String regex) {
		this.runRule(field, Rules.match(regex));
	}

	@Override
	public void maxLength(MetaField<String> field, Number maxLength) {
		this.runRule(field, Rules.maxLength(maxLength));
	}

	@Override
	public void maxRange(MetaField<Number> field, Number maxRange) {
		this.runRule(field, Rules.maxRange(maxRange));
	}

	@Override
	public void minLength(MetaField<String> field, Number minLength) {
		this.runRule(field, Rules.minLength(minLength));
	}

	@Override
	public void minRange(MetaField<Number> field, Number minRange) {
		this.runRule(field, Rules.minRange(minRange));
	}

	@Override
	public void nome(MetaField<String> field) {
		this.runRule(field, Rules.nome);
	}

	@Override
	public void range(MetaField<? extends Number> field, Number minRange, Number maxRange) {
		this.runRule(field, Rules.range(minRange, maxRange));
	}

	@Override
	public void registroAnvisa(MetaField<String> field) {
		this.runRule(field, Rules.registroAnvisa);
	}

	@Override
	public void required(MetaField<?> field) {
		this.runRule(field, Rules.required);
	}

	@Override
	public void senha(MetaField<String> field) {
		this.runRule(field, Rules.senha);
	}

	@Override
	public void telefone(MetaField<String> field) {
		this.runRule(field, Rules.telefone);
	}

}