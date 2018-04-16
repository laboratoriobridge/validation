export type ValidatorFunction = (allValues: object) => any
export type RuleFunction = (value: any) => any
export interface RuleNestedArray {
    [index: number]: RuleFunction | RuleNestedArray
}
export type RuleDefinition = RuleFunction | RuleNestedArray
export type RuleMap = { [key in string]: RuleDefinition }
export type ValidateFunction = (value: any, propertiesErrors: object) => object

export function createValidator(rules: RuleMap, validator?: ValidateFunction): ValidatorFunction {
    return (allValues: object): any => {
        const errors = {}
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

export function validate(value: any, rule: RuleDefinition): any {
    const ruleArray = [].concat.apply([], [].concat(rule)).filter(r => typeof r === 'function')
    const composedFunction = composeRules(ruleArray)
    return composedFunction(value)
}

const composeRules = (rules: RuleFunction[]) => (value) =>
    rules.map(rule => rule(value)).filter(error => !!error)[0 /* retorna apenas o primero erro */]

export const clearErrorObject = (obj) => {
    const cleaned = Object.keys(obj)
        .filter(k => !!obj[k])
        .reduce((newObj, k) => {
            if (typeof obj[k] === 'object') {
                const nestedObj = clearErrorObject(obj[k])
                return nestedObj ? { ...newObj, [k]: nestedObj } : newObj
            } else {
                return { ...newObj, [k]: obj[k] }
            }
        }, {})

    if (Object.keys(cleaned).length === 0) {
        return undefined
    }

    return cleaned
}
