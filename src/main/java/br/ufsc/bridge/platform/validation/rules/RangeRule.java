package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class RangeRule<T extends Number> implements Rule<T> {

	private final T maxRange;
	private final T minRange;

	public RangeRule(T minRange, T maxRange) {
		this.maxRange = maxRange;
		this.minRange = minRange;
	}

	@Override public String validate(T value) {
		if (!Util.isEmpty(value) && (value.doubleValue() < this.minRange.doubleValue() || value.doubleValue() > this.maxRange.doubleValue())) {
			return "Deve ter valor entre " + this.minRange + " e " + this.maxRange;
		}
		return null;
	}

}
