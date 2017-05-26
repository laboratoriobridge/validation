package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class SenhaTest extends ValidationTest {

	@Test
	public void testarRequired() {
		Assert.assertNull("senha não deve ser requerido por padrão", this.validate(null, Rules.senha));

		Assert.assertNull("senha não deve ser requerido por padrão", this.validate("", Rules.senha));
	}

	@Test
	public void senha() {
		Assert.assertNotNull("senha deve conter pelo menos 8 caracteres", this.validate("123a", Rules.senha));

		Assert.assertNotNull("senha deve conter apenas letras e números", this.validate("$@!123asd", Rules.senha));

		Assert.assertNull("senha válida", this.validate("123asd3f", Rules.senha));
	}

}
