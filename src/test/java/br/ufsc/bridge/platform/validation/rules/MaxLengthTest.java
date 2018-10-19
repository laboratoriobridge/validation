package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class MaxLengthTest extends ValidationTest {

	@Test
	public void empty() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.maxLength(3)));
	}

	@Test
	public void min() {
		Assert.assertNotNull("Atributo com length maior que o max é inválido", this.validate("JOAO", Rules.maxLength(3)));
		Assert.assertNull("Atributo com length menor que o max é válido", this.validate("JOA", Rules.maxLength(4)));
	}

}
