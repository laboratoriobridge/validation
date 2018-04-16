import { msg } from '..'
import * as Util from '../Util'

export function empty(value: any) {
    if (!Util.isEmpty(value)) {
        return msg('empty', value)
    }
}
