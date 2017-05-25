package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class CepTest extends ValidationTest {

	private static final CepRule RULE = Rules.cep;

	@Test
	public void testarRequired() {
		Assert.assertNull("Cep não deve ser requerido por padrão", this.validate(null, RULE));
	}

	@Test
	public void cepDeveConterExatamente8Digitos() {
		Assert.assertNull("CEP com 8 dígitos é válido", this.validate("88106580", RULE));

		Assert.assertNotNull("CEP deve conter exatamente 8 digítos", this.validate("881065805", RULE));

		Assert.assertNotNull("CEP deve conter exatamente 8 digítos", this.validate("881", RULE));

		Assert.assertNotNull("CEP deve conter exatamente 8 digítos", this.validate("88106ABC", RULE));
	}
}
