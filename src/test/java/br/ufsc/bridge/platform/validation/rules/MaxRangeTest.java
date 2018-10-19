package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class MaxRangeTest extends ValidationTest {

	@Test
	public void empty() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.maxRange(3)));
	}

	@Test
	public void min() {
		Assert.assertNotNull("Atributo com range maior que o max é inválido", this.validate(5, Rules.maxRange(4)));
		Assert.assertNull("Atributo com range menor que o max é válido", this.validate(4, Rules.maxRange(5)));
	}

}
