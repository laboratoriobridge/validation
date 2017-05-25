import * as Util from '../Util'

var EMAIL = "^[_a-z0-9-\\+]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$";

export function email(value) {
	if (!isValid(value)) {
		return Util.dadosInvalidos('e-mail');
	}
}

function isValid(value) {
	if (!value) {
		return true;
	}
	return new RegExp(EMAIL).test(value);
}