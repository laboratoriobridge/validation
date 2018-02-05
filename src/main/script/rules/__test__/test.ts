import { createValidator, cpf, required, cns } from '../../index'

const validator = createValidator({
    nome: required,
    teste: createValidator({
        id: [required, cpf, [[cns]]],
    }),
})
