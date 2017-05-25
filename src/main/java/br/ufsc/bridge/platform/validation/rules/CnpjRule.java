package br.ufsc.bridge.platform.validation.rules;

import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.engine.Engine;
import br.ufsc.bridge.platform.validation.engine.Rule;

public class CnpjRule implements Rule {

	@Override
	public Object get(Engine engine) throws ScriptException {
		return engine.evalRule("cnpj");
	}

}
