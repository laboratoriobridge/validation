package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class CpfTest extends ValidationTest {

	@Test
	public void testarRequired() {
		Assert.assertNull("Cpf não deve ser requerido por padrão", this.validate(null, Rules.cpf));

		Assert.assertNull("Cpf não deve ser requerido por padrão", this.validate("", Rules.cpf));
	}

	@Test
	public void testarValorDeCpf() {
		Assert.assertNull("01234567890 é um Cpf válido", this.validate("01234567890", Rules.cpf));

		Assert.assertNull("93705505777 é um Cpf válido", this.validate("93705505777", Rules.cpf));

		Assert.assertNull("78270016020 é um Cpf válido", this.validate("78270016020", Rules.cpf));

		Assert.assertNull("49983741768 é um Cpf válido", this.validate("49983741768", Rules.cpf));

		Assert.assertNull("18423143511 é um Cpf válido", this.validate("18423143511", Rules.cpf));

		Assert.assertNotNull("11111111111 não é um Cpf válido", this.validate("11111111111", Rules.cpf));

		Assert.assertNotNull("28371284122 não é um Cpf válido", this.validate("28371284122", Rules.cpf));

		Assert.assertNotNull("89746543121 não é um Cpf válido", this.validate("89746543121", Rules.cpf));

		Assert.assertNotNull("32549687464 não é um Cpf válido", this.validate("32549687464", Rules.cpf));

		Assert.assertNotNull("33216549876 não é um Cpf válido", this.validate("33216549876", Rules.cpf));

		Assert.assertNotNull("15975385246 não é um Cpf válido", this.validate("15975385246", Rules.cpf));

		Assert.assertNotNull("No Cpf são aceitos apenas dígitos", this.validate("012.345.678-90", Rules.cpf));

		Assert.assertNotNull("No Cpf são aceitos apenas dígitos", this.validate("012abcdefg9", Rules.cpf));

		Assert.assertNotNull("No Cpf são aceitos apenas dígitos", this.validate("#123456789$", Rules.cpf));
	}

	@Test
	public void testarTamanho() {
		Assert.assertNotNull("Cpf com menos de 11 dígitos não deve ser aceito", this.validate("0123456", Rules.cpf));

		Assert.assertNotNull("Cpf com mais de 11 dígitos não deve ser aceito",
				this.validate("01234567890123", Rules.cpf));

		Assert.assertNull("Cpf com 11 dígitos deve ser aceito", this.validate("01234567890", Rules.cpf));
	}
}
