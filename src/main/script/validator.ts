export type RuleFunction = (value: any) => any
export interface RuleNestedArray {
    [index: number]: RuleFunction | RuleNestedArray
}
export type Rule = RuleFunction | RuleNestedArray
export type Rules = { [key in string]: Rule }
export type ValidateFunction = (value: any, errors: any) => any

export function createValidator(rules: Rules, validator?: ValidateFunction): RuleFunction {
    return (value: any): any => {
        const errors = {}
        Object.keys(rules).forEach((key) => {
            const error = validate(value && value[key], rules[key])
            if (error) {
                errors[key] = error
            }
        })

        if (validator) {
            Object.assign(errors, validator(value, errors))
        }

        return Object.keys(errors).length > 0 ? errors : undefined
    }
}

export function validate(value: any, rule: Rule): any {
    const ruleArray = [].concat.apply([], [].concat(rule)).filter(r => typeof r === 'function')
    const joinedRules = join(ruleArray)
    return joinedRules(value)
}

const join = (rules) => (value) =>
    rules.map(rule => rule(value)).filter(error => !!error)[0 /* retorna apenas o primero erro */]
