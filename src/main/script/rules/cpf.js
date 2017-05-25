import { length } from './length'
import * as Util from '../Util'

var CPF = "^[0-9]{11}$";

function cpfRule(value) {
    if (!isValid(value)) {
        return Util.dadosInvalidos('CPF');
    }
}

function isSomenteDigitos(cpf) {
    return new RegExp(CPF).test(cpf);
}

function isValid(cpf) {
    if (!cpf) {
        return true;
    }

    if (!isSomenteDigitos(cpf) || verificaCPF(cpf) ) {
        return false;
    }

   
    var sm = 0;
    var peso = 10;
    var dig10, dig11;
    for (var i = 0; i < 9; i++) {
        var num = cpf.charCodeAt(i) - 48;
        sm = sm + num * peso;
        peso = peso - 1;
    }
    var r = 11 - sm % 11;
    dig10 = calculaDigito10(r);
    sm = 0;
    peso = 11;
    for (var a = 0; a < 10; a++) {
        var nume = cpf.charCodeAt(a) - 48;
        sm = sm + nume * peso;
        peso = peso - 1;
    }
    r = 11 - sm % 11;
    dig11 = calcularDigito11(r);

    return dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10);

}

function calcularDigito11(r){
	 if (r == 10 || r == 11) {
	        return '0';
	    } else {
	        return r;
	    }
}

function calculaDigito10(r){
	 if (r == 10 || r == 11) {
	        return '0';
	    } else {
	        return  r;
	    }
}

function verificaCPF(cpf){
	if(verificaCPFIgual(cpf) || verificaCPFIgual2(cpf) ){
		return true
	}
	return false
}

function verificaCPFIgual(cpf){
	if(cpf === "00000000000" || cpf === "11111111111" || cpf === "22222222222"){
		return true;
	}
	if(cpf === "33333333333" || cpf === "44444444444" || cpf === "55555555555"){
		return true;
	}
	
	return false
}

function verificaCPFIgual2(cpf){
	if(cpf === "66666666666" || cpf === "77777777777" || cpf === "88888888888"){
		return true
	}
	if(cpf === "99999999999" || cpf.length != 11){
		return true
	}
	return false
}

export const cpf = [
    length(11),
    cpfRule
]
