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

    @Test
    fun `Atributo com length igual ao max eh valido`() {
        assertNull(validate("<p>12345</p>", richTextMaxLength(5)))
    }

    @Test
    fun `Atributo apenas com tags eh valido`() {
        assertNull(validate("<p><br></p>", richTextMaxLength(0)))
    }

    @Test
    fun `Entidade conta como um unico caractere`() {
        // "&lt;ab&gt;" representa "<ab>", 4 caracteres
        assertNull(validate("<p>&lt;ab&gt;</p>", richTextMaxLength(4)))
        assertNotNull(validate("<p>&lt;ab&gt;</p>", richTextMaxLength(3)))
    }

    @Test
    fun `Entidade numerica conta como um unico caractere`() {
        // "d&#39;agua" representa "d'agua", 6 caracteres
        assertNull(validate("<p>d&#39;agua</p>", richTextMaxLength(6)))
        assertNotNull(validate("<p>d&#39;agua</p>", richTextMaxLength(5)))
    }

    @Test
    fun `Texto com acentos escapados eh medido pelo texto convertido`() {
        // "valida&ccedil;&atilde;o" representa "validação", 9 caracteres
        assertNull(validate("valida&ccedil;&atilde;o", richTextMaxLength(9)))
        assertNotNull(validate("valida&ccedil;&atilde;o", richTextMaxLength(8)))
    }

    @Test
    fun `Quebras de linha sao removidas da contagem`() {
        assertNull(validate("<p>abc</p>\n<p>def</p>", richTextMaxLength(6)))
    }
}
