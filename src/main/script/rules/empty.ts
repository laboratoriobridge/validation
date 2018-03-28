import * as Util from '../Util'

export function empty(value: any) {
    if (!Util.isEmpty(value)) {
        return 'Esse campo não deve ser preenchido'
    }
}
