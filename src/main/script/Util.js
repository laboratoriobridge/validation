export const isEmpty = value => value === undefined || value === null || value === '' || value.length === 0 || value.size ===0;
import Immutable from 'immutable'

export function dadosInvalidos(fieldName) {
    return Immutable.Map({
        titulo: 'Dados inválidos',
        mensagem: `O ${fieldName} informado é inválido`
    });
};