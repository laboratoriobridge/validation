package br.ufsc.bridge.platform.validation.rules

import br.ufsc.bridge.platform.validation.engine.Rule
import br.ufsc.bridge.platform.validation.util.RegexUtil
import br.ufsc.bridge.platform.validation.util.Util
import org.apache.commons.text.StringEscapeUtils

class RichTextMinLengthRule(private val minLength: Int) : Rule<String?> {

    override fun validate(value: String?): String? {
        val text = StringEscapeUtils.unescapeHtml4(value?.replace(RegexUtil.HTML_TAG_PATTERN, ""))
        return if (Util.isEmpty(text) || text.length < minLength) {
            "Deve possuir ao menos $minLength caractere(s)"
        } else null
    }
}

