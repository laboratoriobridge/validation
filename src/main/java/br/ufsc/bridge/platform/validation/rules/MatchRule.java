package br.ufsc.bridge.platform.validation.rules;

import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.engine.Engine;
import br.ufsc.bridge.platform.validation.engine.Rule;

public class MatchRule implements Rule {

	private final String regex;

	public MatchRule(String regex) {
		this.regex = regex;
	}

	@Override
	public Object get(Engine engine) throws ScriptException {
		return engine.evalRule("match(/"+this.regex+"/)");
	}

}
