package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class MaxRangeRule<T extends Number> implements Rule<T> {

	private final T maxRange;

	public MaxRangeRule(T maxRange) {
		this.maxRange = maxRange;
	}

	@Override public String validate(T value) {
		if (!Util.isEmpty(value) && value.doubleValue() > this.maxRange.doubleValue()) {
			return "Deve ser menor ou igual a " + this.maxRange;
		}
		return null;
	}
}
