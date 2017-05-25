import { length } from './length'
import Immutable from 'immutable'

const HORAREGEX = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
const DURACAOREGEX = "^([0-9][0-9]):[0-5][0-9]$";

export function hora(value) {
    if (!isValidHora(value)) {
        return Immutable.Map({
            titulo: 'Dados inválidos',
            mensagem: 'O horário informado é inválido'
        });
    }
}

export function duracao(value) {
    if (!isValidDuracao(value) || value == '00:00') {
        return Immutable.Map({
            titulo: 'Dados inválidos',
            mensagem: 'A duração informada é inválida'
        });
    }
}

function isValidHora(value) {
    if (!value) {
        return true;
    }
    return new RegExp(HORAREGEX).test(value);
}


function isValidDuracao(value) {
    if (!value) {
        return true;
    }
    return new RegExp(DURACAOREGEX).test(value);
}
