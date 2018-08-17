import { match } from '..'
import * as Util from '../Util'

const REGEX_MATCH = /([A-Za-záéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ' ])/

export function nomeRule(value: string) {
    if ((!Util.isEmpty(value) && value.split(' ').length < 2) || verificaNomeSobrenome(value)) {
        return 'Informe nome e sobrenome'
    }
}
function verificaNomeSobrenome(value: string) {
    if (value && value.split(' ').length <= 2 && (value.split(' ')[0].length <= 2 && value.split(' ')[1].length <= 2)) {
        return true
    }
    return false
}

export const nome = [
    match(REGEX_MATCH),
    nomeRule,
]
