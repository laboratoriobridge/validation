package br.ufsc.bridge.platform.validation.rules;

import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.engine.Engine;
import br.ufsc.bridge.platform.validation.engine.Rule;

public class MaxRangeRule implements Rule {

	private final Number maxRange;

	public MaxRangeRule(Number maxRange) {
		this.maxRange = maxRange;
	}

	@Override
	public Object get(Engine engine) throws ScriptException {
		return engine.evalRule("maxRange("+this.maxRange+")");
	}

}
