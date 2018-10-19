package br.ufsc.bridge.platform.validation.form.errors;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

import br.ufsc.bridge.metafy.MetaField;
import br.ufsc.bridge.metafy.MetaList;
import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.rules.Rules;
import br.ufsc.bridge.platform.validation.util.Reflections;

public class FormErrorImpl extends HashMap<String, Object> implements FormError {

	private static final long serialVersionUID = -6446758048046334551L;

	private transient Object target;

	public FormErrorImpl(Object target) {
		super();
		this.target = target;
	}

	private void fieldError(String campo, String mensagem) {
		if (mensagem != null) {
			this.put(campo, mensagem);
		} else {
			this.remove(campo);
		}
	}

	@Override
	public void fieldError(MetaField<?> field, String mensagem) {
		this.fieldError(field.getAlias(), mensagem);
	}

	protected Object getFieldValue(MetaField<?> field) {
		return Reflections.getValue(this.target, field.getAlias());
	}

	@Override
	public FormError formError(MetaField<?> field) {
		FormError error = (FormError) this.get(field.getAlias());
		if (error == null) {
			error = new FormErrorImpl(this.getFieldValue(field));
			this.put(field.getAlias(), error);
		}
		return error;
	}

	@Override
	public ListError listError(MetaList<?> field) {
		ListError error = (ListError) this.get(field.getAlias());
		if (error == null) {
			error = new ListErrorImpl((List<?>) this.getFieldValue(field));
			this.put(field.getAlias(), error);
		}
		return error;
	}

	@Override
	public boolean isValid(MetaField<?> field) {
		boolean valid = true;
		Object error = this.get(field.getAlias());
		if (error instanceof String) {
			valid = false;
		} else if (error instanceof ValidationError) {
			valid = ((ValidationError) error).isValid();
		}
		return valid;
	}

	@Override
	public boolean isValid() {
		boolean valid = true;

		Iterator<Object> iterator = this.values().iterator();
		while (iterator.hasNext()) {
			Object validationError = iterator.next();
			if (validationError instanceof ValidationError) {
				boolean childFormValid = ((ValidationError) validationError).isValid();
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

	@Override
	public <F> FormError check(MetaField<F> field, Rule<F> rule) {
		if (this.isValid(field)) {
			String result;
			if (this.target != null) {
				result = rule.validate((F) Reflections.getValue(this.target, field.getAlias()));
			} else {
				result = rule.validate(null);
			}
			this.fieldError(field, result);
		}
		return this;
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
		this.check(field, Rules.alfaNumerico);
	}

	@Override
	public void beforeToday(MetaField<LocalDate> field) {
		this.check(field, Rules.beforeToday);
	}

	@Override
	public void cep(MetaField<String> field) {
		this.check(field, Rules.cep);
	}

	@Override
	public void cpf(MetaField<String> field) {
		this.check(field, Rules.cpf);
	}

	@Override
	public void cnpj(MetaField<String> field) {
		this.check(field, Rules.cnpj);
	}

	@Override
	public void cns(MetaField<String> field) {
		this.check(field, Rules.cns);
	}

	@Override
	public void email(MetaField<String> field) {
		this.check(field, Rules.email);
	}

	@Override
	public void empty(MetaField<?> field) {
		this.check(field, Rules.empty);
	}

	@Override
	public void hour(MetaField<String> field) {
		this.check(field, Rules.hour);
	}

	@Override
	public void length(MetaField<String> field, int length) {
		this.check(field, Rules.length(length));
	}

	@Override
	public void match(MetaField<String> field, String regex) {
		this.check(field, Rules.match(regex));
	}

	@Override
	public void maxLength(MetaField<String> field, int maxLength) {
		this.check(field, Rules.maxLength(maxLength));
	}

	@Override
	public <T extends Number> void maxRange(MetaField<T> field, T maxRange) {
		this.check(field, Rules.maxRange(maxRange));
	}

	@Override
	public void minLength(MetaField<String> field, int minLength) {
		this.check(field, Rules.minLength(minLength));
	}

	@Override
	public <T extends Number> void minRange(MetaField<T> field, T minRange) {
		this.check(field, Rules.minRange(minRange));
	}

	@Override
	public <T extends Number> void range(MetaField<T> field, T minRange, T maxRange) {
		this.check(field, Rules.range(minRange, maxRange));
	}

	@Override
	public void required(MetaField<?> field) {
		this.check(field, Rules.required);
	}

	@Override
	public void phone(MetaField<String> field) {
		this.check(field, Rules.phone);
	}

	@Override
	public <T> FormError forEach(MetaList<T> field, BiConsumer<T, FormError> itemValidator) {
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.getFieldValue(field);

		if (list == null || list.isEmpty()) {
			return this;
		}

		ListError listErrors = this.listError(field);

		for (int i = 0; i < list.size(); i++) {
			T item = list.get(i);
			itemValidator.accept(item, listErrors.itemError(i));
		}
		return this;
	}

}