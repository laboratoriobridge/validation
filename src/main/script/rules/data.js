import * as Util from '../Util'
import Immutable from 'immutable'
import moment from 'moment'

export const SERVER_FORMAT = 'YYYY-MM-DD'

function minDateFunction(value) {
    if (!Util.isEmpty(value)) {
        const today = moment().toDate();
        let minDate = new Date(today.getFullYear() - 130, today.getMonth(), today.getDate());
        minDate = asFormat(minDate);
        if (minDate > value) {
            return Immutable.Map({
                titulo: 'Data inválida',
                mensagem: 'A idade máxima permitida é 130 anos'
            });
        }
    }
}

function maxDateFunction(value) {
    if (!Util.isEmpty(value)) {
        let maxDate = moment().toDate();
        maxDate = asFormat(maxDate);
        if (maxDate < value) {
            return Immutable.Map({
                titulo: 'Data inválida',
                mensagem: 'A data não pode ser superior à data atual'
            });
        }
    }
}

function asFormat(value) {
    return moment(value).format(SERVER_FORMAT);
}

export const dataNasc = [
    minDateFunction,
    maxDateFunction
]

export const maxDate = [
    maxDateFunction
]