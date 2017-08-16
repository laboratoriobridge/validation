package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class LogradouroTest extends ValidationTest {

	@Test
	public void testarRequired() {
		Assert.assertNull("Logradouro não deve ser requerido por padrão", this.validate(null, Rules.logradouro));

		Assert.assertNull("Logradouro não deve ser requerido por padrão", this.validate("", Rules.logradouro));
	}

	@Test
	public void nomeDeveConterMaisDeUmTermo() {
		Assert.assertNotNull("Logradouro com caracteres especiais é inválido", this.validate("BR$-101", Rules.logradouro));

		Assert.assertNull("Logradouro com hífen, parênteses, barra e vírgula é válido", this.validate("BR-101 (KM 208/209), KM 210", Rules.logradouro));
	}

}
