import { msg } from '..'
import * as Util from '../Util'

export function range(min: number, max: number) {
    return (value: number) => {
        if (!Util.isEmpty(value) && (value < min || value > max)) {
            return msg('range', value, min, max)
        }
    }
}

export function minRange(min: number) {
    return (value: number) => {
        if (!Util.isEmpty(value) && value < min) {
            return msg('minRange', value, min)
        }
    }
}

export function maxRange(max: number) {
    return (value: number) => {
        if (!Util.isEmpty(value) && value > max) {
            return msg('maxRange', value, max)
        }
    }
}
