package br.ufsc.bridge.platform.validation.rules

import br.ufsc.bridge.platform.validation.ValidationTest
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class RichTextMinLengthTest : ValidationTest() {
    @Test
    fun `Atributo null eh invalido`() {
        assertNotNull(validate(null, Rules.richTextMinLength(10)))
    }

    @Test
    fun `Atributo apenas com tags eh invalido`() {
        assertNotNull(validate("<p><br></p>", Rules.richTextMinLength(10)))
    }

    @Test
    fun `Atributo com tags e espacos em branco eh invalido`() {
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

    @Test
    fun `Atributo com length igual ao min eh valido`() {
        assertNull(validate("<p>12345</p>", Rules.richTextMinLength(5)))
    }

    @Test
    fun `Atributo vazio eh invalido`() {
        assertNotNull(validate("", Rules.richTextMinLength(1)))
    }

    @Test
    fun `Entidade conta como um unico caractere`() {
        // "&lt;ab&gt;" representa "<ab>", 4 caracteres
        assertNull(validate("<p>&lt;ab&gt;</p>", Rules.richTextMinLength(4)))
        assertNotNull(validate("<p>&lt;ab&gt;</p>", Rules.richTextMinLength(5)))
    }

    @Test
    fun `Entidade numerica conta como um unico caractere`() {
        // "d&#39;agua" representa "d'agua", 6 caracteres
        assertNull(validate("<p>d&#39;agua</p>", Rules.richTextMinLength(6)))
        assertNotNull(validate("<p>d&#39;agua</p>", Rules.richTextMinLength(7)))
    }

    @Test
    fun `Texto com acentos escapados eh medido pelo texto convertido`() {
        // "valida&ccedil;&atilde;o" representa "validação", 9 caracteres
        assertNull(validate("valida&ccedil;&atilde;o", Rules.richTextMinLength(9)))
        assertNotNull(validate("valida&ccedil;&atilde;o", Rules.richTextMinLength(10)))
    }

    @Test
    fun `Quebras de linha nao contam para o tamanho minimo`() {
        assertNotNull(validate("<p>ab</p>\n<p>cd</p>", Rules.richTextMinLength(5)))
    }
}