import { msg } from '..'
import * as Util from '../Util'

import { minLength } from './length'
import { match } from './match'

const NOT_SPECIAL_CHARS = /^(?=.*[A-Za-z0-9])[A-Za-z0-9]+$/

const SENHA_COMPLETA = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/

function senhaRule(value: string) {
    if (!Util.isEmpty(value) && !SENHA_COMPLETA.test(value)) {
        return msg('senha', value)
    }
}

export const senha = [
    minLength(8),
    match(NOT_SPECIAL_CHARS),
    senhaRule,
]
