import * as Util from '../Util'
import { minLength } from './length'
import Immutable from 'immutable'


var REGEX = /([^A-Za-záéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ' ])/;

export function nome(value) {
	if (!Util.isEmpty(value) && isSomenteLetras(value)) {
		return Immutable.Map({
			titulo: "Caracter não permitido",
			mensagem: "Um ou mais caracteres informados não são permitidos para esse campo"
		});
	}
	if ((!Util.isEmpty(value) && value.split(" ").length < 2) || verificaNomeSobrenome(value)) {
		return Immutable.Map({
			titulo: "Nome inválido",
			mensagem: "Informe nome e sobrenome"
		});
	}
}
function isSomenteLetras(value) {
	return REGEX.test(value);
}

function verificaNomeSobrenome(value) {
	if (value && (value.split(" ")[0].length < 2 || value.split(" ")[1].length < 2)) {
		return true;
	}
	return false;
}
