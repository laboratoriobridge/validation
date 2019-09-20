package br.ufsc.bridge.platform.validation.form.errors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ListErrorImpl implements ListError {

	private static final long serialVersionUID = 6107994429283159738L;

	private transient List<?> target;
	private String rootError;
	private List<FormError> itemErrors = new ArrayList<>();

	public ListErrorImpl(List<?> target) {
		super();
		this.target = target;
	}

	@Override public void error(String mensagem) {
		this.rootError = mensagem;
	}

	@Override
	public FormError itemError(int index) {
		if (this.itemErrors.size() > index) {
			return this.itemErrors.get(index);
		}
		FormError itemError = new FormErrorImpl(this.target.get(index));
		this.itemErrors.add(index, itemError);
		return itemError;

	}
	
	@Override
	public boolean isValid() {
		boolean valid = this.rootError == null;

		if (valid) {
			Iterator<FormError> iterator = this.itemErrors.iterator();
			while (iterator.hasNext()) {
				ValidationError validationError = iterator.next();
				if (validationError instanceof FormErrorImpl) {
					boolean childFormValid = ((FormErrorImpl) validationError).isValid();
					valid = valid && childFormValid;
				} else {
					valid = false;
				}
			}
		}

		return valid;
	}

	@Override public Object getErrors() {
		if (this.rootError != null) {
			return this.rootError;
		}
		return this.itemErrors.stream()
				.map(ValidationError::getErrors)
				.collect(Collectors.toList());
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
		ListErrorImpl other = (ListErrorImpl) obj;
		if (this.target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!this.target.equals(other.target)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.target == null ? 0 : this.target.hashCode());
		return result;
	}

}
