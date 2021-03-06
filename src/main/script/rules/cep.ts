import { msg } from '..'
import * as Util from '../Util'

const REGEX = '^[0-9]{8}$'

export function cep(value: string) {
    if (!Util.isEmpty(value) && !new RegExp(REGEX).test(value)) {
        return msg('cep', value)
    }
}
