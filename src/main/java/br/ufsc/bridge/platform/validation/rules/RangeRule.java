package br.ufsc.bridge.platform.validation.rules;

import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.engine.Engine;
import br.ufsc.bridge.platform.validation.engine.Rule;

public class RangeRule implements Rule {

	private final int maxRange;
	private final int minRange;
	
	public RangeRule(int minRange, int maxRange) {
		this.maxRange = maxRange;
		this.minRange = minRange;
	}
	
	@Override
	public Object get(Engine engine) throws ScriptException {
		return engine.evalRule("range("+this.minRange+", "+this.maxRange+")");
	}

}
