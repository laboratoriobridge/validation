import * as Util from '../Util'

const REGEX = "(1|8)[0-9]{10}$";

export function registroAnvisa(value) {
  if (!Util.isEmpty(value) && !new RegExp(REGEX).test(value)) {
    return Util.dadosInvalidos('Registro ANVISA');
  }
}