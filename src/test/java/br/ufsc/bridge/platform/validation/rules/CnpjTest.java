package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class CnpjTest extends ValidationTest {

	@Test
	public void testarRequired() {
		Assert.assertNull("Cnpj não deve ser requerido por padrão", this.validate(null, Rules.cnpj));

		Assert.assertNull("Cnpj não deve ser requerido por padrão", this.validate("", Rules.cnpj));
	}

	@Test
	public void testarValorDeCnpj() {
		Assert.assertNull("23348194000116 é um Cnpj válido", this.validate("23348194000116", Rules.cnpj));

		Assert.assertNull("10781193000119 é um Cnpj válido", this.validate("10781193000119", Rules.cnpj));

		Assert.assertNull("78471826000126 é um Cnpj válido", this.validate("78471826000126", Rules.cnpj));

		Assert.assertNull("86462814000163 é um Cnpj válido", this.validate("86462814000163", Rules.cnpj));

		Assert.assertNotNull("32432423424234 não é um Cnpj válido", this.validate("32432423424234", Rules.cnpj));

		Assert.assertNotNull("92876904876903 não é um Cnpj válido", this.validate("92876904876903", Rules.cnpj));

		Assert.assertNotNull("85478704024331 não é um Cnpj válido", this.validate("85478704024331", Rules.cnpj));

		Assert.assertNotNull("03216987964642 não é um Cnpj válido", this.validate("03216987964642", Rules.cnpj));

		Assert.assertNotNull("11111111111111 não é um Cnpj válido", this.validate("11111111111111", Rules.cnpj));

		Assert.assertNotNull("32758217350712 não é um Cnpj válido", this.validate("32758217350712", Rules.cnpj));

		Assert.assertNotNull("No Cnpj são aceitos apenas dígitos", this.validate("23.348.194/0001-16", Rules.cnpj));

		Assert.assertNotNull("No Cnpj são aceitos apenas dígitos", this.validate("012abcdefg9539", Rules.cnpj));

		Assert.assertNotNull("No Cnpj são aceitos apenas dígitos", this.validate("#123456789$056", Rules.cnpj));
	}

	@Test
	public void testarTamanho() {
		Assert.assertNotNull("Cnpj com menos de 14 dígitos não deve ser aceito", this.validate("0123456", Rules.cnpj));

		Assert.assertNotNull("Cnpj com mais de 14 dígitos não deve ser aceito", this.validate("01234567890123456", Rules.cnpj));

		Assert.assertNull("Cnpj com 14 dígitos deve ser aceito", this.validate("53110873000113", Rules.cnpj));
	}

}
