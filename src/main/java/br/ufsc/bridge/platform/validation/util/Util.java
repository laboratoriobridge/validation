package br.ufsc.bridge.platform.validation.util;

import java.util.Collection;

public class Util {

	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		} else if (value instanceof String) {
			return ((String)value).trim().equals("");
		} else if (value instanceof Collection) {
			return ((Collection)value).isEmpty();
		}
		return false;
	}

}
