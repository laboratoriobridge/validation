import { createValidator, maxLength, required } from '../'

it('should return an error object containing messages for the invalid attributes', () => {
    const validator = createValidator({
        foo: required,
        bar: [[required], maxLength(1)],
        baz: createValidator({
            biz: [required, [[maxLength(3)]]],
        }),
        valid1: [],
        valid2: maxLength(10),
    })

    const obj = {}
    const errors = validator(obj)
    expect(errors.foo).toBeTruthy()
    expect(errors.bar).toBeTruthy()
    expect(errors.baz).toBeTruthy()
    expect(errors.baz.biz).toBeTruthy()
    expect(errors.valid1).not.toBeTruthy()
    expect(errors.valid2).not.toBeTruthy()
    expect(errors.lala).not.toBeTruthy()
})
