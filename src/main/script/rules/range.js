import * as Util from '../Util'
import Immutable from 'immutable'

export function range(min, max) {
    return value => {
        if (!Util.isEmpty(value) && (value < min || value > max)) {
            return Immutable.Map({
                titulo: 'Dados inválidos',
                mensagem: `O campo deve ter valor entre ${min} e ${max}`
            });
        }
    }
}

export function minRange(min) {
    return value => {
        if (!Util.isEmpty(value) && value < min) {
            return Immutable.Map({
                titulo: 'Dados inválidos',
                mensagem: `O valor inserido deve ser maior ou igual a ${min}`
            });
        }
    }
}

export function maxRange(max) {
    return value => {
        if (!Util.isEmpty(value) && value > max) {
            return Immutable.Map({
                titulo: 'Dados inválidos',
                mensagem: `O valor inserido deve ser menor ou igual a ${max}`
            });
        }
    }
}