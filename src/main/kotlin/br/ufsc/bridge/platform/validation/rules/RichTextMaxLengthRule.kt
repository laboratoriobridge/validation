package br.ufsc.bridge.platform.validation.rules

import br.ufsc.bridge.platform.validation.engine.Rule
import br.ufsc.bridge.platform.validation.util.Util
import org.apache.commons.text.StringEscapeUtils

class RichTextMaxLengthRule(private val maxLength: Int) : Rule<String?> {

    private val htmlTagPattern = Regex("\\<.*?\\>|\n")

    override fun validate(value: String?): String? {
        val text = StringEscapeUtils.unescapeHtml4(value?.replace(htmlTagPattern, ""))
        return if (!Util.isEmpty(text) && text?.length!! > maxLength) "Deve possuir no máximo $maxLength caracteres" else null
    }
}
