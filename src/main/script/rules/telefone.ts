import * as Util from '../Util'

const TELEFONE = '^\\d{2}9?\\d{8}$'

function numeroDoTelefoneTemTodosDigitosIguais(telefoneValue: string) {
    const numero = telefoneValue.substring(2, telefoneValue.length)
    for (let i = 0; i < numero.length; i++) {
        if (numero.charAt(0) !== numero.charAt(i)) {
            return false
        }
    }
    return true
}

export function telefone(value: string) {
    if (!Util.isEmpty(value) && (numeroDoTelefoneTemTodosDigitosIguais(value) || !new RegExp(TELEFONE).test(value))) {
        return 'O telefone informado é inválido'
    }
}
