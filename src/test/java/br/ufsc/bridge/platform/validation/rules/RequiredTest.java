package br.ufsc.bridge.platform.validation.rules;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.platform.validation.ValidationTest;

public class RequiredTest extends ValidationTest {

	@Test
	public void requiredEmCampoSimples() {
		Assert.assertNotNull("Atributo é requerido", this.validate(null, Rules.required));

		Assert.assertNull("Atributo com com qualquer valor é válido", this.validate("JOAQUIM XAVIER", Rules.required));
	}

	@Test
	public void requiredEmList() {
		Assert.assertNotNull("List é requerido", this.validate(null, Rules.required));

		Assert.assertNotNull("List vazio é inválido", this.validate(new ArrayList<String>(), Rules.required));

		List<String> list = new ArrayList<>();
		list.add("1");
		Assert.assertNull("List com items é valido", this.validate(list, Rules.required));
	}

}
