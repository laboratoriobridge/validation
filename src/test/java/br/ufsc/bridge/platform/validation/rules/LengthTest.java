package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class LengthTest extends ValidationTest {

	@Test
	public void empty() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.length(3)));
	}

	@Test
	public void min() {
		Assert.assertNotNull("Atributo com length diferente é inválido", this.validate("JOAO", Rules.length(3)));
		Assert.assertNull("Atributo com length igual é válido", this.validate("JOAO", Rules.length(4)));
	}

}
