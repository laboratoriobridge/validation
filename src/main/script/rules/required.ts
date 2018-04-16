import { msg } from '..'
import * as Util from '../Util'

export function required(value: any) {
    if (Util.isEmpty(value)) {
        return msg('required', value)
    }
}
