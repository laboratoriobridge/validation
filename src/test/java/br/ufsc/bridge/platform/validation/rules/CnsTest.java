package br.ufsc.bridge.platform.validation.rules;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class CnsTest extends ValidationTest {

	@Test
	public void testarRequired() {
		Assert.assertNull("Cns não deve ser requerido por padrão", this.validate(null, Rules.cns));

		Assert.assertNull("Cns não deve ser requerido por padrão", this.validate("", Rules.cns));
	}

	@Test
	public void testarValorDeCns() {
		Assert.assertNull("975055825130003 é um Cns válido", this.validate("975055825130003", Rules.cns));

		Assert.assertNull("586309087428878 é um Cns válido", this.validate("586309087428878", Rules.cns));

		Assert.assertNull("118725165660004 é um Cns válido", this.validate("118725165660004", Rules.cns));

		Assert.assertNull("218342347130008 é um Cns válido", this.validate("218342347130008", Rules.cns));

		Assert.assertNull("787879297840003 é um Cns válido", this.validate("787879297840003", Rules.cns));

		Assert.assertNull("835680589880002 é um Cns válido", this.validate("835680589880002", Rules.cns));

		Assert.assertNull("949504471340005 é um Cns válido", this.validate("949504471340005", Rules.cns));

		Assert.assertNotNull("O Cns é invalido", this.validate("109878998754646", Rules.cns));

		Assert.assertNotNull("O Cns é invalido", this.validate("594516874912612", Rules.cns));

		Assert.assertNotNull("O Cns é invalido", this.validate("236951545487965", Rules.cns));

		Assert.assertNotNull("O Cns é invalido", this.validate("478976532213336", Rules.cns));

		Assert.assertNotNull("O Cns é invalido", this.validate("785002654903214", Rules.cns));

		Assert.assertNotNull("O Cns é invalido", this.validate("895456432144412", Rules.cns));

		Assert.assertNotNull("O Cns é invalido", this.validate("963010024230331", Rules.cns));

		Assert.assertNotNull("O Cns não pode comeaçar com 0", this.validate("035195282210004", Rules.cns));

		Assert.assertNotNull("O Cns não pode comeaçar com 3", this.validate("382411439520003", Rules.cns));

		Assert.assertNotNull("O Cns não pode comeaçar com 4", this.validate("431439904710009", Rules.cns));

		Assert.assertNotNull("O Cns não pode comeaçar com 6", this.validate("667429114440003", Rules.cns));

		Assert.assertNotNull("111111111111111 não é um Cns válido", this.validate("111111111111111", Rules.cns));

		Assert.assertNotNull("287421087523057 não é um Cns válido", this.validate("287421087523057", Rules.cns));

		Assert.assertNotNull("No Cns são aceitos apenas dígitos", this.validate("012abcdefg95392", Rules.cns));

		Assert.assertNotNull("No Cns são aceitos apenas dígitos", this.validate("#123456789$0565", Rules.cns));
	}

	@Test
	public void testarTamanho() {
		Assert.assertNotNull("Cns com menos de 15 dígitos não deve ser aceito", this.validate("10123456", Rules.cns));

		Assert.assertNotNull("Cns com mais de 15 dígitos não deve ser aceito", this.validate("10123456", Rules.cns));

		Assert.assertNull("Cns com 15 dígitos deve ser aceito", this.validate("213274737420002", Rules.cns));
	}

}
