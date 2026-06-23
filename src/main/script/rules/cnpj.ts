import { msg } from ".."

import { length } from "./length"

// Constantes estruturais do CNPJ
const DIVISOR_MODULO_11 = 11
const TAMANHO_BASE_CNPJ = 12

// A regra oficial diz para extrair 48 do valor ASCII do caractere alfanumérico
const OFFSET_ASCII = 48

// Pesos para o cálculo dos Dígitos Verificadores
const PESOS_DV1 = [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]
const PESOS_DV2 = [6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]

export function cnpjRule(value: string) {
  // Ignora valores vazios pois existe a regra do "Required" separada
  if (value && !isValid(value)) {
    return msg("cnpj", value)
  }
}

function isValid(cnpjValue: string) {
  if (cnpjValue?.length !== 14) return false

  // 1. Limpeza e Normalização
  const cnpjLimpo = cnpjValue.replace(/[^a-zA-Z\d]/g, "").toUpperCase()

  // 2. Validação Estrutural (Regex)
  if (!/^[A-Z\d]{12}\d{2}$/.test(cnpjLimpo)) {
    return false
  }

  // 3. Extração dos dígitos informados no final da string para validação
  const dv1Informado = Number.parseInt(cnpjLimpo.charAt(12), 10)
  const dv2Informado = Number.parseInt(cnpjLimpo.charAt(13), 10)

  // 4. Cálculo dos dígitos esperados
  const dv1Calculado = calcularDigitoVerificador(
    cnpjLimpo,
    PESOS_DV1,
    TAMANHO_BASE_CNPJ,
  )
  const dv2Calculado = calcularDigitoVerificador(
    cnpjLimpo,
    PESOS_DV2,
    TAMANHO_BASE_CNPJ + 1,
  )

  // 5. O CNPJ só é válido se ambos os dígitos baterem
  return dv1Calculado === dv1Informado && dv2Calculado === dv2Informado
}

/**
 * Calcula o dígito verificador aplicando o Módulo 11 com a regra alfanumérica.
 */
function calcularDigitoVerificador(
  cnpj: string,
  pesos: number[],
  tamanhoConsiderado: number,
): number {
  let soma = 0

  for (let i = 0; i < tamanhoConsiderado; i++) {
    const codigoAscii = cnpj.charCodeAt(i)

    // Regra da Receita Federal: O valor numérico do caractere para o cálculo
    // equivale ao seu código na tabela ASCII subtraído de 48.
    // Exemplo 1: '0' (ASCII 48) - 48 = 0
    // Exemplo 2: 'A' (ASCII 65) - 48 = 17
    const valorConvertido = codigoAscii - OFFSET_ASCII

    soma += valorConvertido * pesos[i]
  }

  const resto = soma % DIVISOR_MODULO_11

  // Regra padrão do Módulo 11: Se o resto for menor que 2, o dígito é 0.
  // Caso contrário, o dígito é a diferença entre o divisor (11) e o resto.
  return resto < 2 ? 0 : DIVISOR_MODULO_11 - resto
}

export const cnpj = [length(14), cnpjRule]
