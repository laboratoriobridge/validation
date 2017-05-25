import * as Util from '../Util'

const TELEFONE = "^\\d{2}9?\\d{8}$";

function numeroDoTelefoneTemTodosDigitosIguais(telefone) {
	var numero = telefone.substring(2, telefone.length);
	for (var i = 0; i < numero.length; i++) {
		if (numero.charAt(0) != numero.charAt(i)) {
			return false;
		}
	}
	return true;
}

export function telefone(value) {
	if (!Util.isEmpty(value) && (numeroDoTelefoneTemTodosDigitosIguais(value) || !new RegExp(TELEFONE).test(value))) {
		return Util.dadosInvalidos('telefone');
	}
}
