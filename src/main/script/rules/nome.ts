import * as Util from '../Util'

const REGEX = /([^A-Za-záéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ' ])/

export function nome(value: string) {
    if (!Util.isEmpty(value) && isNotSomenteLetras(value)) {
        return 'Um ou mais caracteres informados não são permitidos para esse campo'
    }
    if ((!Util.isEmpty(value) && value.split(' ').length < 2) || verificaNomeSobrenome(value)) {
        return 'Informe nome e sobrenome'
    }
}

function isNotSomenteLetras(value: string) {
    return REGEX.test(value)
}

function verificaNomeSobrenome(value: string) {
    if (value && (value.split(' ')[0].length < 2 || value.split(' ')[1].length < 2)) {
        return true
    }
    return false
}
