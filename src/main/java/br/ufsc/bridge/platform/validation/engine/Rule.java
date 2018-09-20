package br.ufsc.bridge.platform.validation.engine;

import javax.script.ScriptException;

@FunctionalInterface
public interface Rule<T> {

	String validate(T value);

}
