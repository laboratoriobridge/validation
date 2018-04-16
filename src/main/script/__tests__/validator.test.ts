import { clearErrorObject, createValidator, ErrorObject, maxLength, msg, required, validate } from '../'

describe('createValidator', () => {
    it('should create a function that returns an error object containing messages for the invalid attributes', () => {
        const validator = createValidator<any>({
            foo: required,
            bar: [[required], maxLength(1)],
            baz: createValidator({
                biz: [required, [[maxLength(3)]]],
            }),
            valid1: [],
            valid2: maxLength(10),
        })

        const obj = { qux: 'qux', valid1: 'test' }
        const errors = validator(obj)
        expect(errors.foo).toBeTruthy()
        expect(errors.bar).toBeTruthy()
        expect(errors.baz).toBeTruthy()
        expect((errors.baz as any).biz).toBeTruthy()
        expect(errors.valid1).not.toBeTruthy()
        expect(errors.valid2).not.toBeTruthy()
        expect(errors.lala).not.toBeTruthy()
    })

    it('should accept a second all value validation function', () => {
        const validator = createValidator<any>({
            foo: required,
        }, (allValues, propertiesErrors: any) => {
            const errors: ErrorObject<any> = {}
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

    it('should clear out recursively property falsy property errors', () => {
        const validator = createValidator({}, (values) => {
            const errors = {} as any
            errors.foo = required(values.foo)
            errors.bar = { baz: required(values.bar.baz) }
            return errors
        })

        expect(validator({ foo: '1', bar: { baz: '2' } })).toBeUndefined()
        expect(validator({ bar: { baz: '2' } })).toEqual({ foo: msg('required', null) })
        expect(validator({ foo: '1', bar: {} })).toEqual({ bar: { baz: msg('required', null) } })
    })

    it('should create a validator bound to DataType', () => {
        interface DataType {
            id: number
            name: string
            address: {
                street: string
                city: { id: number, name: string }
            }
        }

        const errors: ErrorObject<DataType> = {
            id: 'id error',
            name: 'name error',
            address: {
                street: 'address.street error',
                city: {
                    id: 'address.city.id error',
                    name: 'address.city.name error',
                },
            },
        }

        const validator = createValidator<DataType>({
            id: [],
            name: [],
            address: createValidator({
                street: [],
            }),
        })

        const errors2 = validator({})
    })
})

describe('validate', () => {
    it('should validate the value according to the rules', () => {
        expect(validate('Foo', (value) => !value && 'Wrong')).toBeUndefined()
        expect(validate('', (value) => !value && 'Wrong')).toEqual('Wrong')

        expect(validate('', required)).toBeTruthy()
        expect(validate('', [required])).toBeTruthy()
        expect(validate('', [[required]])).toBeTruthy()
    })
})

describe('clearOut', () => {
    it('should recursively clean falsy object properties', () => {
        const obj = {
            foo: 'Wrong',
            bar: undefined,
            baz: null,
            qux: false,
            biz: '',
            nest: { top: 1, not: false },
            notNest: { deep: '' },
        }
        expect(clearErrorObject(obj)).toEqual({ foo: 'Wrong', nest: { top: 1 } })
    })
})
