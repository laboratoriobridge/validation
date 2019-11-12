package br.ufsc.bridge.platform.validation.rules

import br.ufsc.bridge.platform.validation.engine.Rule
import br.ufsc.bridge.platform.validation.util.Util

class CepRule : Rule<String?> {
    private val match = MatchRule("^[0-9]{8}$")

    override fun validate(value: String?): String? {
        return if (!Util.isEmpty(value) && !this.match.isValid(value)) {
            "Campo é inválido"
        } else null
    }
}
