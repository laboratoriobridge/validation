import Immutable from 'immutable'

export * from './rules/alfaNumerico';
export * from './rules/cep';
export * from './rules/cnpj';
export * from './rules/cns';
export * from './rules/cpf';
export * from './rules/data';
export * from './rules/email';
export * from './rules/hora';
export * from './rules/length';
export * from './rules/match';
export * from './rules/nome';
export * from './rules/range';
export * from './rules/registroAnvisa';
export * from './rules/required';
export * from './rules/senha';
export * from './rules/telefone';

export function createValidator(rules, validator) {
    return (data = Immutable.Map()) => {
        if (!Immutable.Map.isMap(data)) {
            data = Immutable.Map(data)
        }
        const errors = {};
        Object.keys(rules).forEach((key) => {
            const error = validate(data.get(key), rules[key])
            if (error) {
                errors[key] = error;
            }
        });

        if (validator) {
            Object.assign(errors, validator(data, errors));
        }

        return errors;
    }
}

export function validate(value, rule) {
    const ruleArray = [].concat.apply([], [].concat(rule))
    const joinedRules = join(ruleArray); // concat enables both functions and arrays of functions
    return joinedRules(value);
}

export function serverValidate(value, rule) {
    let result = validate(value, rule)
    if (result) {
        result = result.toJS()
    }
    return result;
}

const join = (rules) => (value) => rules.map(rule => rule(value)).filter(error => !!error)[0 /* retorna apenas o primero erro */];