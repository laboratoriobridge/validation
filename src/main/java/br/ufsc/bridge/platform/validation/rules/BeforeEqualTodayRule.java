package br.ufsc.bridge.platform.validation.rules;

import java.time.LocalDate;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class BeforeEqualTodayRule implements Rule<LocalDate> {

	@Override
	public String validate(LocalDate value) {
		if (!Util.isEmpty(value) && value.compareTo(LocalDate.now()) > 0) {
			return "Deve ser posterior ou igual à data atual";
		}
		return null;
	}

}
