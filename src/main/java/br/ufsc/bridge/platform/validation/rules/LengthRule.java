package br.ufsc.bridge.platform.validation.rules;

import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.engine.Engine;
import br.ufsc.bridge.platform.validation.engine.Rule;

public class LengthRule implements Rule {

	private final int length;
	
	public LengthRule(int length) {
		this.length = length;
	}
	
	@Override
	public Object get(Engine engine) throws ScriptException {
		return engine.evalRule("length("+this.length+")");
	}

}
