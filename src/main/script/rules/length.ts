import * as Util from '../Util'

export function length(lengthValue: number) {
    return (value: string) => {
        if (!Util.isEmpty(value) && value.length !== lengthValue) {
            return `O campo deve possuir ${lengthValue} caracteres`
        }
    }
}

export function minLength(min) {
    return value => {
        if (!Util.isEmpty(value) && value.length < min) {
            return `O campo deve possuir ao menos ${min} caracteres`
        }
    }
}

export function maxLength(max) {
    return value => {
        if (!Util.isEmpty(value) && value.length > max) {
            return `O campo deve possuir menos que ${max} caracteres`
        }
    }
}
