import { createValidator, maxLength, required, validate } from '../'

describe('createValidator', () => {
    it('should create a function that returns an error object containing messages for the invalid attributes', () => {
        const validator = createValidator({
            foo: required,
            bar: [[required], maxLength(1)],
            baz: createValidator({
                biz: [required, [[maxLength(3)]]],
            }),
            qux: (value: any, allValues: any) => value + allValues.valid1,
            valid1: [],
            valid2: maxLength(10),
        })

        const obj = { qux: 'qux', valid1: 'test' }
        const errors = validator(obj)
        expect(errors.foo).toBeTruthy()
        expect(errors.bar).toBeTruthy()
        expect(errors.baz).toBeTruthy()
        expect(errors.baz.biz).toBeTruthy()
        expect(errors.qux).toEqual('quxtest')
        expect(errors.valid1).not.toBeTruthy()
        expect(errors.valid2).not.toBeTruthy()
        expect(errors.lala).not.toBeTruthy()
    })

    it('should accept a second all value validation function', () => {
        const validator = createValidator({
            foo: required,
        }, (allValues, propertiesErrors: any) => {
            const errors = {} as any
            if (!allValues.bar) {
                errors.bar = 'Wrong'
            }
            if (propertiesErrors.foo) {
                errors.fooCopy = propertiesErrors.foo
            }
            return errors
        })

        const err = validator({})
        expect(err.foo).toBeTruthy()
        expect(err.bar).toEqual('Wrong')
        expect(err.fooCopy).toEqual(err.foo)

        expect(validator({ foo: 1, bar: 2 })).toBeUndefined()
    })
})

describe('validate', () => {
    it('should validate the value according to the rules', () => {
        expect(validate((value) => !value && 'Wrong', 'Foo', {})).toBeUndefined()
        expect(validate((value) => !value && 'Wrong', '', {})).toEqual('Wrong')

        expect(validate(required, '', {})).toBeTruthy()
        expect(validate([required], '', {})).toBeTruthy()
        expect(validate([[required]], '', {})).toBeTruthy()
    })
})
