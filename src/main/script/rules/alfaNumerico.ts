import * as Util from '../Util'

const REGEX = /([^A-Za-z0-9áéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ ])/

export function alfaNumerico(value: string) {
    if (!Util.isEmpty(value) && isNotAlfaNumerico(value)) {
        return 'Um ou mais caracteres informados não são permitidos para esse campo'
    }
}

function isNotAlfaNumerico(value) {
    return REGEX.test(value)
}
