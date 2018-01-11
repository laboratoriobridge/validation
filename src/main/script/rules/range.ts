import * as Util from '../Util'

export function range(min: number, max: number) {
    return (value: number) => {
        if (!Util.isEmpty(value) && (value < min || value > max)) {
            return `O campo deve ter valor entre ${min} e ${max}`
        }
    }
}

export function minRange(min: number) {
    return (value: number) => {
        if (!Util.isEmpty(value) && value < min) {
            return `O valor inserido deve ser maior ou igual a ${min}`
        }
    }
}

export function maxRange(max: number) {
    return (value: number) => {
        if (!Util.isEmpty(value) && value > max) {
            return `O valor inserido deve ser menor ou igual a ${max}`
        }
    }
}
