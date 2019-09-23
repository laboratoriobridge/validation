package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class MinRangeRule<T extends Number> implements Rule<T> {

	private final T minRange;

	public MinRangeRule(T minRange) {
		this.minRange = minRange;
	}

	@Override public String validate(T value) {
		if (!Util.isEmpty(value) && value.doubleValue() < this.minRange.doubleValue()) {
			return "Deve ser maior ou igual a " + this.minRange;
		}
		return null;
	}

}
