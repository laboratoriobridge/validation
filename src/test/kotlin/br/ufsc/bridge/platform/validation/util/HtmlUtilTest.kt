package br.ufsc.bridge.platform.validation.util

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HtmlUtilTest {

    @Test
    fun `Null retorna null`() {
        assertNull(HtmlUtil.unescapeHtml(null))
    }

    @Test
    fun `String vazia retorna vazia`() {
        assertEquals("", HtmlUtil.unescapeHtml(""))
    }

    @Test
    fun `String sem entidades eh mantida`() {
        assertEquals("Lorem ipsum dolor", HtmlUtil.unescapeHtml("Lorem ipsum dolor"))
    }

    @Test
    fun `Entidades basicas sao convertidas`() {
        assertEquals("<teste>", HtmlUtil.unescapeHtml("&lt;teste&gt;"))
        assertEquals("a & b", HtmlUtil.unescapeHtml("a &amp; b"))
        assertEquals("\"aspas\"", HtmlUtil.unescapeHtml("&quot;aspas&quot;"))
    }

    @Test
    fun `Entidades ISO-8859-1 sao convertidas`() {
        assertEquals(" ", HtmlUtil.unescapeHtml("&nbsp;"))
        assertEquals("ação", HtmlUtil.unescapeHtml("a&ccedil;&atilde;o"))
        assertEquals("é É à Ü ñ ÿ", HtmlUtil.unescapeHtml("&eacute; &Eacute; &agrave; &Uuml; &ntilde; &yuml;"))
        assertEquals("© ® ° ÷ ×", HtmlUtil.unescapeHtml("&copy; &reg; &deg; &divide; &times;"))
    }

    @Test
    fun `Entidades de simbolos e letras gregas sao convertidas`() {
        assertEquals("α β Ω π", HtmlUtil.unescapeHtml("&alpha; &beta; &Omega; &pi;"))
        assertEquals("• … ™ €", HtmlUtil.unescapeHtml("&bull; &hellip; &trade; &euro;"))
        assertEquals("← → ∑ ≠ ≤", HtmlUtil.unescapeHtml("&larr; &rarr; &sum; &ne; &le;"))
        assertEquals("‘aspas’ “duplas” – —", HtmlUtil.unescapeHtml("&lsquo;aspas&rsquo; &ldquo;duplas&rdquo; &ndash; &mdash;"))
    }

    @Test
    fun `Entidades numericas decimais sao convertidas`() {
        assertEquals("A", HtmlUtil.unescapeHtml("&#65;"))
        assertEquals("d'agua", HtmlUtil.unescapeHtml("d&#39;agua"))
        assertEquals("ç", HtmlUtil.unescapeHtml("&#231;"))
    }

    @Test
    fun `Entidades numericas hexadecimais sao convertidas`() {
        assertEquals("A", HtmlUtil.unescapeHtml("&#x41;"))
        assertEquals("A", HtmlUtil.unescapeHtml("&#X41;"))
        assertEquals("'", HtmlUtil.unescapeHtml("&#x27;"))
    }

    @Test
    fun `Entidade numerica fora do BMP gera par substituto`() {
        assertEquals("💩", HtmlUtil.unescapeHtml("&#128169;"))
        assertEquals("💩", HtmlUtil.unescapeHtml("&#x1F4A9;"))
    }

    @Test
    fun `Entidade desconhecida eh mantida`() {
        assertEquals("&desconhecida;", HtmlUtil.unescapeHtml("&desconhecida;"))
        assertEquals("&apos;", HtmlUtil.unescapeHtml("&apos;"))
    }

    @Test
    fun `Entidade sem ponto e virgula eh mantida`() {
        assertEquals("&amp", HtmlUtil.unescapeHtml("&amp"))
        assertEquals("&#65", HtmlUtil.unescapeHtml("&#65"))
        assertEquals("a & b", HtmlUtil.unescapeHtml("a & b"))
    }

    @Test
    fun `Entidade malformada eh mantida`() {
        assertEquals("&;", HtmlUtil.unescapeHtml("&;"))
        assertEquals("&#;", HtmlUtil.unescapeHtml("&#;"))
        assertEquals("&#x;", HtmlUtil.unescapeHtml("&#x;"))
        assertEquals("&#12ab;", HtmlUtil.unescapeHtml("&#12ab;"))
        assertEquals("&#-12;", HtmlUtil.unescapeHtml("&#-12;"))
        assertEquals("&#1114112;", HtmlUtil.unescapeHtml("&#1114112;"))
    }

    @Test
    fun `Escape duplo eh convertido apenas uma vez`() {
        assertEquals("&amp;", HtmlUtil.unescapeHtml("&amp;amp;"))
        assertEquals("&lt;", HtmlUtil.unescapeHtml("&amp;lt;"))
    }

    @Test
    fun `Entidades sao sensiveis a maiusculas e minusculas`() {
        assertEquals("Σ σ", HtmlUtil.unescapeHtml("&Sigma; &sigma;"))
        assertEquals("&AMP;", HtmlUtil.unescapeHtml("&AMP;"))
    }

    @Test
    fun `Texto com varias entidades misturadas eh convertido`() {
        assertEquals(
            "São José <1 & >2  fim",
            HtmlUtil.unescapeHtml("S&atilde;o Jos&eacute; &lt;1 &amp; &gt;2 &nbsp;fim")
        )
    }

    @Test
    fun `E comercial seguido de entidade valida converte apenas a entidade`() {
        assertEquals("&<", HtmlUtil.unescapeHtml("&&lt;"))
        assertEquals("&foo<", HtmlUtil.unescapeHtml("&foo&lt;"))
    }
}
