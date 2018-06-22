export type ValidatorFunction<DataType> = (allValues: Partial<DataType>) => ErrorObject<DataType>
export type RuleFunction = (value: any) => any
export interface RuleNestedArray {
    [index: number]: RuleFunction | RuleNestedArray
}
export type RuleDefinition = RuleFunction | RuleNestedArray
export type RuleMap<DataType> = { [key in keyof Partial<DataType>]: RuleDefinition }
export type ErrorObject<DataType> = {
    [K in keyof DataType]?: ErrorObject<DataType[K]> | string
}
export type ValidateFunction<DataType> = (value: DataType, errors: ErrorObject<DataType>) => ErrorObject<DataType>

export function createValidator<DataType = any>(rules: RuleMap<DataType>, validator?: ValidateFunction<DataType>):
    ValidatorFunction<DataType> {
    return (allValues: DataType): ErrorObject<DataType> => {
        const errors: ErrorObject<DataType> = {}

        Object.keys(rules).forEach((key) => {
            const error = validate(allValues && allValues[key], rules[key])
            if (error) {
                errors[key] = error
            }
        })

        if (validator) {
            Object.assign(errors, validator(allValues, errors))
        }

        return clearErrorObject(errors)
    }
}

export function createListValidator<DataType = any>(itemValidator: ValidatorFunction<DataType>):
    ValidatorFunction<DataType[]> {
    return (itemList: DataType[]): ErrorObject<DataType[]> => {
        const errors: Array<ErrorObject<DataType>> = []

        if (!itemList) {
            return null
        }
        let hasErrors = false

        itemList.map((value, index) => {
            errors[index] = itemValidator(value)
            if (errors[index]) {
                hasErrors = true
            }
        })

        if (!hasErrors) {
            return null
        }

        return errors
    }
}

export function validate(value: any, rule: RuleDefinition): any {
    const ruleArray = [].concat.apply([], [].concat(rule)).filter(r => typeof r === 'function')
    const composedFunction = composeRules(ruleArray)
    return composedFunction(value)
}

const composeRules = (rules: RuleFunction[]) => (value) =>
    rules.map(rule => rule(value)).filter(error => !!error)[0 /* returns first error only */]

export const clearErrorObject = <T>(errors: ErrorObject<T>): ErrorObject<T> | undefined => {
    const cleaned = Object.keys(errors)
        .filter(k => !!errors[k])
        .reduce((newObj, k) => {
            if (typeof errors[k] === 'object') {
                const nestedObj = clearErrorObject(errors[k])
                return nestedObj ? { ...newObj, [k]: nestedObj } : newObj
            } else {
                return { ...newObj, [k]: errors[k] }
            }
        }, {})

    if (Object.keys(cleaned).length === 0) {
        return undefined
    }

    return cleaned
}
