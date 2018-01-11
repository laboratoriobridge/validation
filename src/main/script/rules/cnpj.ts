import { length } from './length'

function cnpjRule(value: string) {
    if (!isValid(value)) {
        return 'O CNPJ informado é inválido'
    }
}

function isValid(cnpjValue: string) {
    if (!cnpjValue) {
        return true
    }
    if (cnpjValue === ('00000000000000')
        || cnpjValue === ('11111111111111')
        || cnpjValue === ('22222222222222')
        || cnpjValue === ('33333333333333')
        || cnpjValue === ('44444444444444')
        || cnpjValue === ('55555555555555')
        || cnpjValue === ('66666666666666')
        || cnpjValue === ('77777777777777')
        || cnpjValue === ('88888888888888')
        || cnpjValue === ('99999999999999')
        || cnpjValue.length !== 14) {
        return false
    }
    let tamanho = cnpjValue.length - 2
    let numeros = cnpjValue.substring(0, tamanho)
    const digitos = cnpjValue.substring(tamanho)
    let soma = 0
    let pos = tamanho - 7
    for (let i = tamanho; i >= 1; i--) {
        soma += parseInt(numeros.charAt(tamanho - i)) * pos--
        if (pos < 2) {
            pos = 9
        }
    }
    let resultado = soma % 11 < 2 ? 0 : 11 - soma % 11
    if (resultado !== parseInt(digitos.charAt(0))) {
        return false
    }

    tamanho = tamanho + 1
    numeros = cnpjValue.substring(0, tamanho)
    soma = 0
    pos = tamanho - 7
    for (let i = tamanho; i >= 1; i--) {
        soma += parseInt(numeros.charAt(tamanho - i)) * pos--
        if (pos < 2) {
            pos = 9
        }
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11
    if (resultado !== parseInt(digitos.charAt(1))) {
        return false
    }

    return true
}

export const cnpj = [
    length(14),
    cnpjRule,
]
