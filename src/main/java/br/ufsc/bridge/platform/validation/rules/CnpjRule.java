package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class CnpjRule implements Rule<String> {

	@Override public String validate(String value) {
		if (!Util.isEmpty(value) && !this.isValid(value)) {
			return "CNPJ inválido";
		}

		return null;
	}

	public boolean isValid(String cnpjValue) {
		if (!cnpjValue.matches("^[0-9]*$")) {
			return false;
		}
		if (cnpjValue == "00000000000000"
				|| cnpjValue == "11111111111111"
				|| cnpjValue == "22222222222222"
				|| cnpjValue == "33333333333333"
				|| cnpjValue == "44444444444444"
				|| cnpjValue == "55555555555555"
				|| cnpjValue == "66666666666666"
				|| cnpjValue == "77777777777777"
				|| cnpjValue == "88888888888888"
				|| cnpjValue == "99999999999999"
				|| cnpjValue.length() != 14) {
			return false;
		}
		int tamanho = cnpjValue.length() - 2;
		String numeros = cnpjValue.substring(0, tamanho);
		String digitos = cnpjValue.substring(tamanho);
		int soma = 0;
		int pos = tamanho - 7;
		for (int i = tamanho; i >= 1; i--) {
			soma += Integer.parseInt(numeros.charAt(tamanho - i) + "") * pos--;
			if (pos < 2) {
				pos = 9;
			}
		}
		int resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		if (resultado != Integer.parseInt(digitos.charAt(0) + "")) {
			return false;
		}

		tamanho = tamanho + 1;
		numeros = cnpjValue.substring(0, tamanho);
		soma = 0;
		pos = tamanho - 7;
		for (int i = tamanho; i >= 1; i--) {
			soma += Integer.parseInt(numeros.charAt(tamanho - i) + "") * pos--;
			if (pos < 2) {
				pos = 9;
			}
		}
		resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		if (resultado != Integer.parseInt(digitos.charAt(1) + "")) {
			return false;
		}
		return true;
	}
}
