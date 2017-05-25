package br.ufsc.bridge.platform.validation.rules;

import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.engine.Engine;
import br.ufsc.bridge.platform.validation.engine.Rule;

public class MaxLengthRule implements Rule {

	private final int maxLength;
	
	public MaxLengthRule(int maxLength) {
		this.maxLength = maxLength;
	}
	
	@Override
	public Object get(Engine engine) throws ScriptException {
		return engine.evalRule("maxLength("+this.maxLength+")");
	}

}
