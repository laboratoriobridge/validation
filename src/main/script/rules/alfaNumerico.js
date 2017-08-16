import * as Util from '../Util'
import Immutable from 'immutable'
var REGEX = /([^A-Za-z0-9áéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ ])/;

export function alfaNumerico(value) {
	if (!Util.isEmpty(value) && isNotAlfaNumerico(value)) {
		return Immutable.Map({
			titulo: "Caracter não permitido",
			mensagem: "Um ou mais caracteres informados não são permitidos para esse campo"
		});
	}

}
function isNotAlfaNumerico(value) {
	return REGEX.test(value);
}
