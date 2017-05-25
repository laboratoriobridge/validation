import { length } from './length'
import * as Util from '../Util'

var CNS1 = "^[1-2][0-9]{10}00[0-1][0-9]$";
var CNS2 = "^[7-9][0-9]{14}$";

function somaPonderada(s) {
    var soma = 0;
    for (var i = 0; i < s.length; i++) {
        soma += (s.charCodeAt(i) - 48) * (15 - i);
    }
    return soma;
}

function cnsRule(cns) {
    if (!isValid(cns)) {
        return Util.dadosInvalidos('CNS');
    }
}

function isValid(cns) {
    if (!cns) {
        return true;
    }
    if (new RegExp(CNS1).test(cns) || new RegExp(CNS2).test(cns)) {
        return somaPonderada(cns) % 11 == 0;
    }
    return false;
}

export const cns = [
    length(15),
    cnsRule
]
