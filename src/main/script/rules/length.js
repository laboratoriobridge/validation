import * as Util from '../Util'
import Immutable from 'immutable'

export function length(length) {
    return value => {
        if (!Util.isEmpty(value) && value.length != length)
            return Immutable.Map( {
                titulo: 'Tamanho do campo',
                mensagem: `O campo deve possuir ${length} caracteres`
            });
    }
}

export function minLength(min) {
    return value => {
        if (!Util.isEmpty(value) && value.length < min) {
            return Immutable.Map( {
                titulo: 'Tamanho do campo',
                mensagem: `O campo deve possuir ao menos ${min} caracteres`
            });
        }
    }
}

export function maxLength(max) {
    return value => {
        if (!Util.isEmpty(value) && value.length > max) {
            return Immutable.Map( {
                titulo: 'Tamanho do campo',
                mensagem: `O campo deve possuir menos que ${max} caracteres`
            });
        }
    }
}