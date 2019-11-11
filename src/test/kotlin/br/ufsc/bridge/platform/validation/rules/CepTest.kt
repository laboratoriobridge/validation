package br.ufsc.bridge.platform.validation.rules

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class CepTest {

    @Test
    fun `testar required`() {
        assertNull(Rules.cep.validate(null), "Cep não deve ser requerido por padrão")
    }

    @Test
    fun `deve conter exatamente 8 digitos`() {
        assertNull(Rules.cep.validate("88106580"), "CEP com 8 dígitos é válido")

        assertNotNull(Rules.cep.validate("881065805"), "CEP deve conter exatamente 8 digítos")

        assertNotNull(Rules.cep.validate("881"), "CEP deve conter exatamente 8 digítos")

        assertNotNull(Rules.cep.validate("88106ABC"), "CEP deve conter exatamente 8 digítos")
    }

    companion object {
        private val RULE = Rules.cep
    }
}
