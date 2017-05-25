package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class TelefoneTest extends ValidationTest {

	@Test
	public void testarRequired() {
		Assert.assertNull("Telefone não deve ser requerido por padrão", this.validate(null, Rules.telefone));

		Assert.assertNull("Telefone não deve ser requerido por padrão", this.validate("", Rules.telefone));
	}

	@Test
	public void somenteDigitosSaoPermitidos() {
		Assert.assertNull("Número do telefone contendo apenas dígitos é válido",
				this.validate("4896317416", Rules.telefone));

		Assert.assertNotNull("Número do telefone deve conter apenas dígitos",
				this.validate("489631741A", Rules.telefone));

		Assert.assertNotNull("Número do telefone deve conter apenas dígitos",
				this.validate("Y489631741", Rules.telefone));

		Assert.assertNotNull("Número do telefone deve conter apenas dígitos",
				this.validate("AIOSDJAOÇI", Rules.telefone));
	}

	@Test
	public void aInformacaoDeNúmeroDeTelefoneEDDDdeveConter10ou11digitos() {
		Assert.assertNull("Número com 11 dígitos é válido", this.validate("48996317416", Rules.telefone));

		Assert.assertNull("Número com 10 dígitos é válido", this.validate("4896311234", Rules.telefone));

		Assert.assertNotNull("Número com 9 dígitos é inválido", this.validate("496311234", Rules.telefone));

		Assert.assertNotNull("Número com 8 dígitos é inválido", this.validate("96311234", Rules.telefone));

		Assert.assertNotNull("Número com 12 dígitos é inválido", this.validate("012345678901", Rules.telefone));

		Assert.assertNotNull("Número com 11 dígitos é válido, desde que o primeiro número seja 9",
				this.validate("48496317418", Rules.telefone));
	}

	@Test
	public void aInformacaoDeNúmeroDeTelefoneNaoPodeTerTodosOsDigitosRepetidos() {
		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("2300000000", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("2511111111", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("1622222222", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("7633333333", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("6544444444", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("2655555555", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("4666666666", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("8777777777", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("7388888888", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("3499999999", Rules.telefone));

		Assert.assertNotNull("Número de telefone com todos os dígitos iguais é inválido",
				this.validate("13999999999", Rules.telefone));
	}

}
