package br.ufsc.bridge.platform.validation.rules;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class EmptyTest extends ValidationTest {

	@Test
	public void emptyEmCampoSimples() {
		Assert.assertNull("Atributo null é válido", this.validate(null, Rules.empty));

		Assert.assertNotNull("Atributo com com qualquer valor é inválido", this.validate("JOAQUIM XAVIER", Rules.empty));
	}

	@Test
	public void emptyEmList() {
		Assert.assertNull("List null é válido", this.validate(null, Rules.empty));

		Assert.assertNull("List vazio é válido", this.validate(new ArrayList<String>(), Rules.empty));

		List<String> list = new ArrayList<>();
		list.add("1");
		Assert.assertNotNull("List com items é inválido", this.validate(list, Rules.empty));
	}

}
