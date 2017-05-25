import * as Util from '../Util'
import Immutable from 'immutable'

export function required(value) {
    if (Util.isEmpty(value)) {
        return Immutable.Map({
            titulo: 'Campo obrigatório',
            mensagem: 'Esse campo é de preenchimento obrigatório'
        })
    }
}