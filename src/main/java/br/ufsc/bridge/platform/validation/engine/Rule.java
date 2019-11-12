package br.ufsc.bridge.platform.validation.engine;

@FunctionalInterface
public interface Rule<T> {

	String validate(T value);

}
