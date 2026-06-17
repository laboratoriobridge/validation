package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class CnpjRule implements Rule<String> {

	// Constantes estruturais do CNPJ
	private static final int TAMANHO_BASE_CNPJ = 12;
	private static final int DIVISOR_MODULO_11 = 11;

	// A regra oficial diz para extrair 48 do valor ASCII do caractere alfanumérico
	private static final int OFFSET_ASCII = 48;

	// Pesos para o cálculo dos Dígitos Verificadores
	private static final int[] PESOS_DV1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	private static final int[] PESOS_DV2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	@Override public String validate(String value) {
		// Ignora valores vazios pois existe a regra do "Required" separada
		if (!Util.isEmpty(value) && !this.isValid(value)) {
			return "Campo inválido";
		}

		return null;
	}

	public boolean isValid(String cnpjValue) {
		if (cnpjValue == null || cnpjValue.length() != 14) {
			return false;
		}

		// 1. Limpeza e Normalização: Remove pontuações e garante maiúsculas
		String cnpjLimpo = cnpjValue.replaceAll("[^a-zA-Z\\d]", "").toUpperCase();

		// 2. Validação Estrutural (Regex)
		// Exige 12 caracteres alfanuméricos seguidos obrigatoriamente por 2 numéricos
		if (!cnpjLimpo.matches("^[A-Z\\d]{12}\\d{2}$")) {
			return false;
		}

		// 3. Extrai os dígitos verificadores informados na string
		int dv1Informado = Character.getNumericValue(cnpjLimpo.charAt(12));
		int dv2Informado = Character.getNumericValue(cnpjLimpo.charAt(13));

		// 4. Calcula os dígitos reais esperados com base na raiz
		int dv1Calculado = calcularDigitoVerificador(cnpjLimpo, PESOS_DV1, TAMANHO_BASE_CNPJ);
		int dv2Calculado = calcularDigitoVerificador(cnpjLimpo, PESOS_DV2, TAMANHO_BASE_CNPJ + 1);

		// 5. O CNPJ só é válido se ambos os dígitos baterem
		return (dv1Informado == dv1Calculado) && (dv2Informado == dv2Calculado);
	}

	/**
	 * Calcula o dígito verificador aplicando o Módulo 11 com a regra alfanumérica.
	 */
	private static int calcularDigitoVerificador(String cnpj, int[] pesos, int tamanhoConsiderado) {
		int soma = 0;

		for (int i = 0; i < tamanhoConsiderado; i++) {
			char caractere = cnpj.charAt(i);

			// Regra da Receita Federal: O valor numérico do caractere para o cálculo
			// equivale ao seu código na tabela ASCII subtraído de 48.
			// Exemplo 1: '0' (ASCII 48) - 48 = 0
			// Exemplo 2: 'A' (ASCII 65) - 48 = 17
			int valorConvertido = caractere - OFFSET_ASCII;

			soma += valorConvertido * pesos[i];
		}

		int resto = soma % DIVISOR_MODULO_11;

		// Regra padrão do Módulo 11: Se o resto for menor que 2, o dígito é 0.
		// Caso contrário, o dígito é a diferença entre o divisor (11) e o resto.
		return resto < 2 ? 0 : (DIVISOR_MODULO_11 - resto);
	}
}
