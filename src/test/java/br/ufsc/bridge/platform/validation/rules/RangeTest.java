package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class RangeTest extends ValidationTest {

	@Test
	public void empty() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.range(3, 9)));
	}

	@Test
	public void min() {
		Assert.assertNotNull("Atributo fora do range é inválido", this.validate(3, Rules.range(4, 6)));
		Assert.assertNull("Atributo dentro do range é válido", this.validate(4, Rules.range(4, 6)));
	}

}
