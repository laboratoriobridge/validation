import * as Util from '../Util'

import { minLength } from './length'

const NOT_SPECIAL_CHARS = /^(?=.*[A-Za-z0-9])[A-Za-z0-9]+$/

const SENHA_COMPLETA = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/

function senhaRule(value: string) {
    if (!Util.isEmpty(value)) {
        if (!NOT_SPECIAL_CHARS.test(value)) {
            return 'Um ou mais caracteres informados não são permitidos para esse campo'
        }
        if (!SENHA_COMPLETA.test(value)) {
            return 'A senha deve possuir ao menos uma letra e um número'
        }
    }
}

export const senha = [
    minLength(8),
    senhaRule,
]
