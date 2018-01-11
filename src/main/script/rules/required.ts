import * as Util from '../Util'

export function required(value: any) {
    if (Util.isEmpty(value)) {
        return 'Esse campo é de preenchimento obrigatório'
    }
}
