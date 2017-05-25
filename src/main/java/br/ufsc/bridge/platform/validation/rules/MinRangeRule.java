package br.ufsc.bridge.platform.validation.rules;

import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.engine.Engine;
import br.ufsc.bridge.platform.validation.engine.Rule;

public class MinRangeRule implements Rule {

	private final int minRange;
	
	public MinRangeRule(int minRange) {
		this.minRange = minRange;
	}
	
	@Override
	public Object get(Engine engine) throws ScriptException {
		return engine.evalRule("minRange("+this.minRange+")");
	}

}
