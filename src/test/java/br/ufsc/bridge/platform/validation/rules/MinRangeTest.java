package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class MinRangeTest extends ValidationTest {

	@Test
	public void empty() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.minRange(3)));
	}

	@Test
	public void min() {
		Assert.assertNotNull("Atributo com range menor que o min é inválido", this.validate(3, Rules.minRange(4)));
		Assert.assertNull("Atributo com range maior que o min é válido", this.validate(4, Rules.minRange(3)));
	}

}
