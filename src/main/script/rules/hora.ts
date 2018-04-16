import { msg } from '..'

const HORAREGEX = '^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$'
const DURACAOREGEX = '^([0-9][0-9]):[0-5][0-9]$'

export function hora(value: string) {
    if (!isValidHora(value)) {
        return msg('hora', value)
    }
}

export function duracao(value: string) {
    if (!isValidDuracao(value) || value === '00:00') {
        return msg('duracao', value)
    }
}

function isValidHora(value) {
    if (!value) {
        return true
    }
    return new RegExp(HORAREGEX).test(value)
}

function isValidDuracao(value) {
    if (!value) {
        return true
    }
    return new RegExp(DURACAOREGEX).test(value)
}
