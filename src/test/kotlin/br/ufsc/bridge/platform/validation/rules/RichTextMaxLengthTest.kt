package br.ufsc.bridge.platform.validation.rules

import br.ufsc.bridge.platform.validation.ValidationTest
import br.ufsc.bridge.platform.validation.rules.Rules.richTextMaxLength
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class RichTextMaxLengthTest : ValidationTest() {
    @Test
    fun `Atributo null eh valido`() {
        assertNull(validate(null, richTextMaxLength(10)))
    }

    @Test
    fun `Atributo com length maior que o max eh invalido`() {
        assertNotNull(validate("Lorem <em>dolor</em> sit <strong>ipsum</strong>.", richTextMaxLength(20)))
    }

    @Test
    fun `Atributo com length menor que o max eh valido`(){
        assertNull(validate("Lorem <em>dolor</em> sit <strong>ipsum</strong>.", richTextMaxLength(25)))
    }
}
