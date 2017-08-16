import * as Util from '../Util'
import Immutable from 'immutable'
var REGEX = /([^A-Za-z0-9áéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ \(\)\-,/])/;

export function logradouro(value) {
	if (!Util.isEmpty(value) && notMatchLogradouro(value)) {
		return Immutable.Map({
			titulo: "Caracter não permitido",
			mensagem: "Um ou mais caracteres informados não são permitidos para esse campo"
		});
	}

}
function notMatchLogradouro(value) {
	return REGEX.test(value);
}
