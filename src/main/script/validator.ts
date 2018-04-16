export type ValidatorFunction = (allValues: object) => any
export type RuleFunction = (value: any, allValues: object) => any
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
            const error = validate(rules[key], allValues && allValues[key], allValues)
            if (error) {
                errors[key] = error
            }
        })

        if (validator) {
            Object.assign(errors, validator(allValues, errors))
        }

        return Object.keys(errors).length > 0 ? errors : undefined
    }
}

export function validate(rule: RuleDefinition, value: any, allValues: object = {}): any {
    const ruleArray = [].concat.apply([], [].concat(rule)).filter(r => typeof r === 'function')
    const composedFunction = composeRules(ruleArray)
    return composedFunction(value, allValues)
}

const composeRules = (rules: RuleFunction[]) => (value, allValues) =>
    rules.map(rule => rule(value, allValues)).filter(error => !!error)[0 /* retorna apenas o primero erro */]
