import { msg } from '..'
import * as Util from '../Util'

export function match(regex: RegExp) {
    return (value: string) => {
        if (!Util.isEmpty(value) && !regex.test(value)) {
            return msg('match', value, regex)
        }
    }
}
