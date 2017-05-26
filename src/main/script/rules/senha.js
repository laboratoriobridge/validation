import { minLength } from './length'
import Immutable from 'immutable'
import * as Util from '../Util'

const NOT_SPECIAL_CHARS = /^(?=.*[A-Za-z0-9])[A-Za-z0-9]+$/;

var SENHA_COMPLETA = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

function senhaRule(value) {
    if (!Util.isEmpty(value)) {
        if (!NOT_SPECIAL_CHARS.test(value)) {
            return Immutable.Map({
                titulo: "Caracter não permitido",
                mensagem: "Um ou mais caracteres informados não são permitidos para esse campo"
            });
        }
        if (!SENHA_COMPLETA.test(value)) {
            return Immutable.Map({
                titulo: "Letra e número requeridos",
                mensagem: "A senha deve possuir ao menos uma letra e um número"
            });
        }
    }
}

export var senha = [
    minLength(8),
    senhaRule
];
