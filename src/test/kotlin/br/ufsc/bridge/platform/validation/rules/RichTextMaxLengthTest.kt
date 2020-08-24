package br.ufsc.bridge.platform.validation.rules

import br.ufsc.bridge.platform.validation.ValidationTest
import br.ufsc.bridge.platform.validation.rules.Rules.richTextMaxLength
import org.junit.Assert
import org.junit.jupiter.api.Test

class RichTextMaxLengthTest : ValidationTest() {
    @Test
    fun empty() {
        Assert.assertNull("Atributo null é válido", validate(null, richTextMaxLength(10)))
    }

    @Test
    fun min() {
        Assert.assertNotNull("Atributo com length <strong>maior</strong> que o max é inválido", validate("Lorem <em>dolor</em> sit <strong>ipsum</strong>.", richTextMaxLength(20)))
        Assert.assertNull("Atributo com length menor que o max é válido", validate("Lorem <em>dolor</em> sit <strong>ipsum</strong>.", richTextMaxLength(25)))
    }
}
