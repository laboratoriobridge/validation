package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class EmailTest extends ValidationTest {

	@Test
	public void testarRequired() {
		Assert.assertNull("Email não deve ser requerido por padrão", this.validate(null, Rules.email));

		Assert.assertNull("Email não deve ser requerido por padrão", this.validate("", Rules.email));
	}

	@Test
	public void emailDeveConterOSimboloArrobaENoMinimoUmPonto() {
		Assert.assertNull("Email deve conter o símbolo arroba e no mínimo um ponto",
				this.validate("umemail@hotmail.com", Rules.email));

		Assert.assertNotNull("Email sem arroba é inválido", this.validate("umemailhotmail.com", Rules.email));

		Assert.assertNotNull("Email sem ponto é inválido", this.validate("umemail@hotmailcom", Rules.email));

		Assert.assertNotNull("Email sem ponto e sem arroba é inválido",
				this.validate("umemailhotmailcom", Rules.email));
	}

}
