package br.ufsc.bridge.platform.validation.form.errors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListErrorImpl extends ArrayList implements ListError {

	private static final long serialVersionUID = 6107994429283159738L;

	private transient List<?> target;

	public ListErrorImpl(List<?> target) {
		super();
		this.target = target;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FormError itemError(int index) {
		FormErrorImpl itemErrors = new FormErrorImpl(this.target.get(index));
		this.add(index, itemErrors);
		return itemErrors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid() {
		boolean valid = true;

		Iterator<ValidationError> iterator = this.iterator();
		while (iterator.hasNext()) {
			ValidationError validationError = iterator.next();
			if (validationError instanceof FormErrorImpl) {
				boolean childFormValid = ((FormErrorImpl) validationError).isValid();
				valid = valid && childFormValid;
			} else {
				valid = false;
			}
		}

		return valid;
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
