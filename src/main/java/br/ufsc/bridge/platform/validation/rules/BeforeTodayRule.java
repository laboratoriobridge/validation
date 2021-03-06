package br.ufsc.bridge.platform.validation.rules;

import java.time.LocalDate;

import br.ufsc.bridge.platform.validation.engine.Rule;
import br.ufsc.bridge.platform.validation.util.Util;

public class BeforeTodayRule implements Rule<LocalDate> {

	@Override
	public String validate(LocalDate value) {
		if (!Util.isEmpty(value) && !value.isBefore(LocalDate.now())) {
			return "Deve ser anterior à data atual";
		}
		return null;
	}

}
