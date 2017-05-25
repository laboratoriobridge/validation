package br.ufsc.bridge.platform.validation.rules;

import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.engine.Engine;
import br.ufsc.bridge.platform.validation.engine.Rule;

public class MinLengthRule implements Rule {

	private final int minLength;
	
	public MinLengthRule(int minLength) {
		this.minLength = minLength;
	}
	
	@Override
	public Object get(Engine engine) throws ScriptException {
		return engine.evalRule("minLength("+this.minLength+")");
	}

}
