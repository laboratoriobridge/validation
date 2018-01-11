const EMAIL = '^[_a-z0-9-\\+]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$'

export function email(value: string) {
    if (!isValid(value)) {
        return 'O e-mail informado é inválido'
    }
}

function isValid(value) {
    if (!value) {
        return true
    }
    return new RegExp(EMAIL).test(value)
}
