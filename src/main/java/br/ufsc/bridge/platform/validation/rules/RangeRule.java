package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class RangeRule implements Rule<Number> {

	private final Number maxRange;
	private final Number minRange;

	public RangeRule(Number minRange, Number maxRange) {
		this.maxRange = maxRange;
		this.minRange = minRange;
	}

	@Override public String validate(Number value) {
		if (!Util.isEmpty(value) && (value.doubleValue() < this.minRange.doubleValue() || value.doubleValue() > this.maxRange.doubleValue())) {
			return "Deve ter valor entre " + this.minRange + " e " + this.maxRange;
		}
		return null;
	}

}
