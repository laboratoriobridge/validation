package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class MaxRangeRule implements Rule<Number> {

	private final Number maxRange;

	public MaxRangeRule(Number maxRange) {
		this.maxRange = maxRange;
	}

	@Override public String validate(Number value) {
		if (!Util.isEmpty(value) && value.doubleValue() > this.maxRange.doubleValue()) {
			return "Valor inserido deve ser menor ou igual a " + this.maxRange;
		}
		return null;
	}
}
