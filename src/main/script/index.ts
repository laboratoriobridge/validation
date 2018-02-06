export * from './rules/alfaNumerico'
export * from './rules/cep'
export * from './rules/cnpj'
export * from './rules/cns'
export * from './rules/cpf'
export * from './rules/data'
export * from './rules/email'
export * from './rules/hora'
export * from './rules/length'
export * from './rules/match'
export * from './rules/nome'
export * from './rules/range'
export * from './rules/required'
export * from './rules/senha'
export * from './rules/telefone'

export type RuleFunction = (value: any) => any
export interface RuleNestedArray {
    [index: number]: RuleFunction | RuleNestedArray
}
export type Rule = RuleFunction | RuleNestedArray
export type Rules = {[key in string]: Rule}
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

        return errors
    }
}

export function validate(value: any, rule: Rule): any {
    const ruleArray = [].concat.apply([], [].concat(rule))
    const joinedRules = join(ruleArray) // concat enables both functions and arrays of functions
    return joinedRules(value)
}

const join = (rules) => (value) =>
    rules.map(rule => rule(value)).filter(error => !!error)[0 /* retorna apenas o primero erro */]
