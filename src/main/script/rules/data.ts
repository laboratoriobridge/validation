import * as moment from 'moment'

import * as Util from '../Util'

export const SERVER_FORMAT = 'YYYY-MM-DD'

function minDateFunction(value: string) {
    if (!Util.isEmpty(value)) {
        const today = moment().toDate()
        const minDate = new Date(today.getFullYear() - 130, today.getMonth(), today.getDate())
        const minDateFormatted = asFormat(minDate)
        if (minDateFormatted > value) {
            return 'A idade máxima permitida é 130 anos'
        }
    }
}

function maxDateFunction(value: string) {
    if (!Util.isEmpty(value)) {
        const maxDateFormatted = asFormat(moment().toDate())
        if (maxDateFormatted < value) {
            return 'A data não pode ser superior à data atual'
        }
    }
}

function asFormat(value) {
    return moment(value).format(SERVER_FORMAT)
}

export const dataNasc = [
    minDateFunction,
    maxDateFunction,
]

export const maxDate = [
    maxDateFunction,
]
