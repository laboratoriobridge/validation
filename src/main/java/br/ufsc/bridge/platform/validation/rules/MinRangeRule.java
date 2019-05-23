package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class MinRangeRule implements Rule<Number> {

	private final Number minRange;

	public MinRangeRule(Number minRange) {
		this.minRange = minRange;
	}

	@Override public String validate(Number value) {
		if (!Util.isEmpty(value) && value.doubleValue() < this.minRange.doubleValue()) {
			return "Deve ser maior ou igual a " + this.minRange;
		}
		return null;
	}

}
