package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class HoraTest extends ValidationTest {

	@Test
	public void empty() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.hour));
	}

	@Test
	public void min() {
		Assert.assertNotNull("Atributo é inválido", this.validate("99:99", Rules.hour));
		Assert.assertNull("Atributo é válido", this.validate("10:15", Rules.hour));
	}

}
