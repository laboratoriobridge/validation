package br.ufsc.bridge.platform.validation.rules

import br.ufsc.bridge.platform.validation.ValidationTest
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class RichTextMinLengthTest : ValidationTest() {
    @Test
    fun `Atributo null nao eh valido`() {
        assertNotNull(validate(null, Rules.richTextMinLength(10)))
    }

    @Test
    fun `Atributo apenas com tags nao eh valido`() {
        assertNotNull(validate("<p><br></p>", Rules.richTextMinLength(10)))
    }

    @Test
    fun `Atributo com tags e espacos em branco nao eh valido`() {
        assertNotNull(validate("<p>  </p>", Rules.richTextMinLength(1)))
    }

    @Test
    fun `Atributo com length menor que o min eh invalido`() {
        assertNotNull(validate("<p>&lt;teste&gt;</p>", Rules.richTextMinLength(8)))
    }

    @Test
    fun `Atributo com length maior que o min eh valido`(){
        assertNull(validate("Lorem <em>dolor</em> sit <strong>ipsum</strong>.", Rules.richTextMinLength(10)))
    }
}