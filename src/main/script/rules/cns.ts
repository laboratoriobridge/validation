import { msg } from '..'

import { length } from './length'

const CNS1 = '^[1-2][0-9]{10}00[0-1][0-9]$'
const CNS2 = '^[7-9][0-9]{14}$'

function somaPonderada(cnsValue: string) {
    let soma = 0
    for (let i = 0; i < cnsValue.length; i++) {
        soma += (cnsValue.charCodeAt(i) - 48) * (15 - i)
    }
    return soma
}

function cnsRule(cnsValue: string) {
    if (!isValid(cnsValue)) {
        return msg('cns', cnsValue)
    }
}

function isValid(cnsValue: string) {
    if (!cnsValue) {
        return true
    }
    if (new RegExp(CNS1).test(cnsValue) || new RegExp(CNS2).test(cnsValue)) {
        return somaPonderada(cnsValue) % 11 === 0
    }
    return false
}

export const cns = [
    length(15),
    cnsRule,
]
