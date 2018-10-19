package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class MinLengthTest extends ValidationTest {

	@Test
	public void empty() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.minLength(3)));
	}

	@Test
	public void min() {
		Assert.assertNotNull("Atributo com length menor que o min é inválido", this.validate("JOA", Rules.minLength(4)));
		Assert.assertNull("Atributo com length maior que o min é válido", this.validate("JOAQ", Rules.minLength(4)));
	}

}
