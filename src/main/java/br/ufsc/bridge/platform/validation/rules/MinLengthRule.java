package br.ufsc.bridge.platform.validation.rules;

import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.engine.Engine;
import br.ufsc.bridge.platform.validation.engine.Rule;

public class MinLengthRule implements Rule {

	private final Number minLength;

	public MinLengthRule(Number minLength) {
		this.minLength = minLength;
	}

	@Override
	public Object get(Engine engine) throws ScriptException {
		return engine.evalRule("minLength("+this.minLength+")");
	}

}
