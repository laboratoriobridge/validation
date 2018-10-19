package br.ufsc.bridge.platform.validation.rules;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class BeforeTodayTest extends ValidationTest {

	@Test
	public void empty() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.beforeToday));
	}

	@Test
	public void min() {
		Assert.assertNotNull("Atributo é inválido", this.validate(LocalDate.now().plusDays(1), Rules.beforeToday));
		Assert.assertNull("Atributo é válido", this.validate(LocalDate.now().minusDays(1), Rules.beforeToday));
	}

}
