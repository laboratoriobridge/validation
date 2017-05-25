package br.ufsc.bridge.platform.validation.engine;

import javax.script.ScriptException;

@FunctionalInterface
public interface Rule {

	public Object get(Engine engine) throws ScriptException;

}
