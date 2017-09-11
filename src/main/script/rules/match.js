import * as Util from '../Util'
import Immutable from 'immutable'

export function match(regex) {
	return value => {
		if (!Util.isEmpty(value) && !regex.test(value)) {
			return Immutable.Map({
				titulo: "Caracter não permitido",
				mensagem: "Um ou mais caracteres informados não são permitidos para esse campo"
			});
		}
	}
}