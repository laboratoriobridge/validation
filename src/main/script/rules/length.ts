import { msg } from '..'
import * as Util from '../Util'

export function length(lengthValue: number) {
    return (value: string) => {
        if (!Util.isEmpty(value) && value.length !== lengthValue) {
            return msg('length', value, lengthValue)
        }
    }
}

export function minLength(min) {
    return value => {
        if (!Util.isEmpty(value) && value.length < min) {
            return msg('minLength', value, min)
        }
    }
}

export function maxLength(max) {
    return value => {
        if (!Util.isEmpty(value) && value.length > max) {
            return msg('maxLength', value, max)
        }
    }
}
