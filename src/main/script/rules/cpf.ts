import { length } from './length'

const CPF = '^[0-9]{11}$'

function cpfRule(value: string) {
    if (!isValid(value)) {
        return 'O CPF informado é inválido'
    }
}

function isSomenteDigitos(cpfValue) {
    return new RegExp(CPF).test(cpfValue)
}

function isValid(cpfValue: string) {
    if (!cpfValue) {
        return true
    }

    if (!isSomenteDigitos(cpfValue) || verificaCPF(cpfValue)) {
        return false
    }

    let sm = 0
    let peso = 10
    let dig10
    let dig11
    for (let i = 0; i < 9; i++) {
        const num = cpfValue.charCodeAt(i) - 48
        sm = sm + num * peso
        peso = peso - 1
    }
    let r = 11 - sm % 11
    dig10 = calculaDigito10(r)
    sm = 0
    peso = 11
    for (let a = 0; a < 10; a++) {
        const nume = cpfValue.charCodeAt(a) - 48
        sm = sm + nume * peso
        peso = peso - 1
    }
    r = 11 - sm % 11
    dig11 = calcularDigito11(r)

    return dig10 === parseInt(cpfValue.charAt(9)) && dig11 === parseInt(cpfValue.charAt(10))

}

function calcularDigito11(r) {
    if (r === 10 || r === 11) {
        return 0
    } else {
        return r
    }
}

function calculaDigito10(r) {
    if (r === 10 || r === 11) {
        return 0
    } else {
        return r
    }
}

function verificaCPF(cpfValue) {
    if (verificaCPFIgual(cpfValue) || verificaCPFIgual2(cpfValue)) {
        return true
    }
    return false
}

function verificaCPFIgual(cpfValue) {
    if (cpfValue === '00000000000' || cpfValue === '11111111111' || cpfValue === '22222222222') {
        return true
    }
    if (cpfValue === '33333333333' || cpfValue === '44444444444' || cpfValue === '55555555555') {
        return true
    }

    return false
}

function verificaCPFIgual2(cpfValue) {
    if (cpfValue === '66666666666' || cpfValue === '77777777777' || cpfValue === '88888888888') {
        return true
    }
    if (cpfValue === '99999999999' || cpfValue.length !== 11) {
        return true
    }
    return false
}

export const cpf = [
    length(11),
    cpfRule,
]
