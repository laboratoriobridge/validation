import * as moment from 'moment'

import { msg } from '..'
import * as Util from '../Util'

export const SERVER_FORMAT = 'YYYY-MM-DD'

function dataNascRule(value: string) {
    if (!Util.isEmpty(value)) {
        const today = moment().toDate()
        const minDate = new Date(today.getFullYear() - 130, today.getMonth(), today.getDate())
        const minDateFormatted = asFormat(minDate)
        if (minDateFormatted > value) {
            return msg('dataNasc', value)
        }
    }
}

function maxDateFunction(value: string) {
    if (!Util.isEmpty(value)) {
        const maxDateFormatted = asFormat(moment().toDate())
        if (maxDateFormatted < value) {
            return msg('maxDate', value)
        }
    }
}

function asFormat(value) {
    return moment(value).format(SERVER_FORMAT)
}

export const dataNasc = [
    dataNascRule,
    maxDateFunction,
]

export const maxDate = [
    maxDateFunction,
]
