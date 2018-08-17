package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class NomeTest extends ValidationTest {

	@Test
	public void testarRequired() {
		Assert.assertNull("Nome não deve ser requerido por padrão", this.validate(null, Rules.nome));

		Assert.assertNull("Nome não deve ser requerido por padrão", this.validate("", Rules.nome));
	}

	@Test
	public void nomeDeveConterMaisDeUmTermo() {
		Assert.assertNotNull("Nome com 1 termo é inválido", this.validate("JOAQUIM", Rules.nome));

		Assert.assertNull("Nome com 2 termos é válido", this.validate("JOAQUIM XAVIER", Rules.nome));

		Assert.assertNull("Nome com 3 ou mais termos é válido", this.validate("JOAQUIM XAVIER SANTOS", Rules.nome));

	}

	@Test
	public void nomeSobrenomeDeveSerMaiorQue2Caracteres() {
		Assert.assertNull("Nome assim é valido", this.validate("AAA BB", Rules.nome));

		Assert.assertNull("Nome assim é valido", this.validate("AA BBB", Rules.nome));

		Assert.assertNotNull("Nome assim é inválido", this.validate("AA BB", Rules.nome));

		Assert.assertNull("Este nome é valido", this.validate("AA BB CCCC", Rules.nome));

	}

}
