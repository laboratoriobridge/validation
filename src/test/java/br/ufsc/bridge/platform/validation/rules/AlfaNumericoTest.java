package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class AlfaNumericoTest extends ValidationTest {

	@Test
	public void empty() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.alfaNumerico));
	}

	@Test
	public void min() {
		Assert.assertNotNull("Atributo é inválido", this.validate("!@#2awsd", Rules.alfaNumerico));
		Assert.assertNull("Atributo é válido", this.validate("123", Rules.alfaNumerico));
	}

}
