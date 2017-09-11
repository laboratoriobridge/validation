package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class MatchTest extends ValidationTest {

	private static final String REGEX = "^([A-Za-z0-9áéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ\\(\\)\\-\\,/\\' ]+)$";

	@Test
	public void testarRequired() {
		Assert.assertNull("Match não deve ser requerido por padrão", this.validate(null, Rules.match(REGEX)));

		Assert.assertNull("Match não deve ser requerido por padrão", this.validate("", Rules.match(REGEX)));
	}

	@Test
	public void nomeDeveConterMaisDeUmTermo() {
		Assert.assertNotNull("Match com caracteres especiais é inválido", this.validate("BR$-101", Rules.match(REGEX)));
		Assert.assertNull("Match com hífen, parênteses, barra, vírgula e apóstrofo é válido", this.validate("BR-101 (KM 208/209), KM 210 d'água", Rules.match(REGEX)));
	}

}
