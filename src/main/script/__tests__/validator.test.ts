import {
    clearErrorObject, createListValidator, createValidator, ErrorObject, maxLength, msg, required, validate
} from '../'

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

describe('clearErrorObject', () => {
    it('should recursively clean falsy object properties', () => {
        const obj = {
            foo: 'Wrong',
            bar: undefined,
            baz: null,
            qux: false,
            biz: '',
            nest: { top: 1, not: false },
            notNest: { deep: '' },
            arr: [],
        }
        expect(clearErrorObject(obj)).toEqual({ foo: 'Wrong', nest: { top: 1 } })
    })
    it('should ignore primitive values', () => {
        expect(clearErrorObject('string')).toEqual('string')
        expect(clearErrorObject(2)).toEqual(2)
        expect(clearErrorObject(null)).toEqual(null)
        expect(clearErrorObject(undefined)).toEqual(undefined)
    })
    it('should preserve array objects', () => {
        const obj = {
            foo: 'bar',
            baz: ['error1', 'error2'],
        }
        // tslint:disable no-string-literal
        obj.baz['global'] = 'global error'

        const cleaned = clearErrorObject(obj)
        expect(Array.isArray(cleaned.baz)).toEqual(true)
        expect(cleaned.baz[0]).toEqual('error1')
        expect(cleaned.baz[1]).toEqual('error2')
        expect(cleaned.baz['global']).toEqual('global error')
    })
    it('should preserve array objects containing only keys', () => {
        const obj = []
        obj['global'] = 'Global error'

        const cleaned = clearErrorObject(obj)
        expect(Array.isArray(cleaned)).toEqual(true)
        expect(cleaned['global']).toEqual('Global error')

        const obj2 = []
        obj2['global'] = undefined
        expect(clearErrorObject(obj2)).toEqual(undefined)
    })
})

interface CustomDataType {
    id: number
    foo: string
    bar: string
}
const itemValidator = createValidator<CustomDataType>({
    id: [required],
    foo: [required],
    bar: [required],
})
const listValidator = createListValidator(itemValidator)

describe('createListValidator', () => {
    it('should return a function that validates a list using specified ValidatorFunction', () => {
        const items: CustomDataType[] = [
            { id: 1, foo: 'foo', bar: 'bar' },
            { id: 2, foo: null, bar: 'bar' },
            { id: 3, foo: 'foo', bar: 'bar' },
        ]

        const errors = listValidator(items)

        expect(errors).toHaveLength(3)
        expect(errors[0]).toBeUndefined()
        expect(errors[2]).toBeUndefined()
        expect(errors[1]).toBeTruthy()
    })

    it('should handle null objects', () => {
        const errors = listValidator(null)

        expect(errors).toBeNull()
    })

    it('should throw an error if object to be validated it is not an array', () => {
        const item = { id: 1, foo: 'foo', bar: 'bar' }

        expect(() => listValidator(item)).toThrow()
    })

    it('should return null if all validations pass', () => {
        const items: CustomDataType[] = [
            { id: 1, foo: 'foo', bar: 'bar' },
            { id: 2, foo: 'foo', bar: 'bar' },
            { id: 3, foo: 'foo', bar: 'bar' },
        ]
        const errors = listValidator(items)

        expect(errors).toBeNull()
    })

})
