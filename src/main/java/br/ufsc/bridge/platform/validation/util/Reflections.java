package br.ufsc.bridge.platform.validation.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.ufsc.bridge.platform.validation.exception.EngineExecutionException;

public class Reflections {

	private Reflections() {

	}

	public static Method getSetter(Class<?> clazz, String fieldName, Class<Boolean> fieldType) {
		String setterName = "set" + getMethodName(fieldName);
		try {
			return clazz.getMethod(setterName, fieldType);
		} catch (NoSuchMethodException e) {
			throw new EngineExecutionException("Setter " + setterName + " não encontrado para o atributo na classe " + clazz.getName(), e);
		}
	}

	public static Method getGetter(Class<?> clazz, String fieldName) {
		String getterName = getMethodName(fieldName);
		getterName = "get" + getterName;
		try {
			return clazz.getMethod(getterName);
		} catch (NoSuchMethodException e) {
			throw new EngineExecutionException("Getter " + getterName + " não encontrado para o atributo na classe " + clazz.getName(), e);
		}
	}

	public static Object getValue(Object instance, String fieldName) {
		Method getter = getGetter(instance.getClass(), fieldName);
		try {
			return getter.invoke(instance);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new EngineExecutionException("Erro ao invocar getter " + getter.getName() + " da classe " + instance.getClass().getName(), e);
		}
	}

	private static String getMethodName(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}
