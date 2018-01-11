import { createValidator, required } from '../../index'

const validator = createValidator({
    nome: required,
    teste: createValidator({
        id: [required],
    }),
})
