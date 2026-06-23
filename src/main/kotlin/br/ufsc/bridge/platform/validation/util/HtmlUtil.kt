package br.ufsc.bridge.platform.validation.util

object HtmlUtil {

    fun unescapeHtml(value: String?): String? {
        if (value == null || value.indexOf('&') < 0) return value
        val result = StringBuilder(value.length)
        var index = 0
        while (index < value.length) {
            val char = value[index]
            if (char != '&') {
                result.append(char)
                index++
                continue
            }
            val semicolonIndex = value.indexOf(';', index + 1)
            if (semicolonIndex < 0) {
                result.append(value, index, value.length)
                break
            }
            val unescaped = unescapeEntity(value.substring(index + 1, semicolonIndex))
            if (unescaped != null) {
                result.append(unescaped)
                index = semicolonIndex + 1
            } else {
                result.append('&')
                index++
            }
        }
        return result.toString()
    }

    private fun unescapeEntity(entity: String): String? {
        if (entity.isEmpty()) return null
        if (entity[0] != '#') return NAMED_ENTITIES[entity]?.toString()
        val isHex = entity.length > 1 && (entity[1] == 'x' || entity[1] == 'X')
        val codePoint = if (isHex) entity.substring(2).toIntOrNull(16) else entity.substring(1).toIntOrNull()
        return if (codePoint != null && codePoint in 0..0x10FFFF) String(Character.toChars(codePoint)) else null
    }

    // Entidades definidas pelo HTML 4.0 ("apos" nao faz parte do HTML 4 e por isso nao eh tratada)
    private val NAMED_ENTITIES: Map<String, Char> = HashMap<String, Char>().apply {
        put("quot", '"')
        put("amp", '&')
        put("lt", '<')
        put("gt", '>')

        // Entidades ISO-8859-1, com codigos sequenciais a partir de 160
        listOf(
            "nbsp", "iexcl", "cent", "pound", "curren", "yen", "brvbar", "sect",
            "uml", "copy", "ordf", "laquo", "not", "shy", "reg", "macr",
            "deg", "plusmn", "sup2", "sup3", "acute", "micro", "para", "middot",
            "cedil", "sup1", "ordm", "raquo", "frac14", "frac12", "frac34", "iquest",
            "Agrave", "Aacute", "Acirc", "Atilde", "Auml", "Aring", "AElig", "Ccedil",
            "Egrave", "Eacute", "Ecirc", "Euml", "Igrave", "Iacute", "Icirc", "Iuml",
            "ETH", "Ntilde", "Ograve", "Oacute", "Ocirc", "Otilde", "Ouml", "times",
            "Oslash", "Ugrave", "Uacute", "Ucirc", "Uuml", "Yacute", "THORN", "szlig",
            "agrave", "aacute", "acirc", "atilde", "auml", "aring", "aelig", "ccedil",
            "egrave", "eacute", "ecirc", "euml", "igrave", "iacute", "icirc", "iuml",
            "eth", "ntilde", "ograve", "oacute", "ocirc", "otilde", "ouml", "divide",
            "oslash", "ugrave", "uacute", "ucirc", "uuml", "yacute", "thorn", "yuml"
        ).forEachIndexed { offset, name -> put(name, (160 + offset).toChar()) }

        // Entidades de simbolos, letras gregas e pontuacao
        listOf(
            "fnof" to 402,
            "Alpha" to 913, "Beta" to 914, "Gamma" to 915, "Delta" to 916, "Epsilon" to 917,
            "Zeta" to 918, "Eta" to 919, "Theta" to 920, "Iota" to 921, "Kappa" to 922,
            "Lambda" to 923, "Mu" to 924, "Nu" to 925, "Xi" to 926, "Omicron" to 927,
            "Pi" to 928, "Rho" to 929, "Sigma" to 931, "Tau" to 932, "Upsilon" to 933,
            "Phi" to 934, "Chi" to 935, "Psi" to 936, "Omega" to 937,
            "alpha" to 945, "beta" to 946, "gamma" to 947, "delta" to 948, "epsilon" to 949,
            "zeta" to 950, "eta" to 951, "theta" to 952, "iota" to 953, "kappa" to 954,
            "lambda" to 955, "mu" to 956, "nu" to 957, "xi" to 958, "omicron" to 959,
            "pi" to 960, "rho" to 961, "sigmaf" to 962, "sigma" to 963, "tau" to 964,
            "upsilon" to 965, "phi" to 966, "chi" to 967, "psi" to 968, "omega" to 969,
            "thetasym" to 977, "upsih" to 978, "piv" to 982,
            "bull" to 8226, "hellip" to 8230, "prime" to 8242, "Prime" to 8243, "oline" to 8254,
            "frasl" to 8260, "weierp" to 8472, "image" to 8465, "real" to 8476, "trade" to 8482,
            "alefsym" to 8501,
            "larr" to 8592, "uarr" to 8593, "rarr" to 8594, "darr" to 8595, "harr" to 8596,
            "crarr" to 8629, "lArr" to 8656, "uArr" to 8657, "rArr" to 8658, "dArr" to 8659,
            "hArr" to 8660,
            "forall" to 8704, "part" to 8706, "exist" to 8707, "empty" to 8709, "nabla" to 8711,
            "isin" to 8712, "notin" to 8713, "ni" to 8715, "prod" to 8719, "sum" to 8721,
            "minus" to 8722, "lowast" to 8727, "radic" to 8730, "prop" to 8733, "infin" to 8734,
            "ang" to 8736, "and" to 8743, "or" to 8744, "cap" to 8745, "cup" to 8746,
            "int" to 8747, "there4" to 8756, "sim" to 8764, "cong" to 8773, "asymp" to 8776,
            "ne" to 8800, "equiv" to 8801, "le" to 8804, "ge" to 8805, "sub" to 8834,
            "sup" to 8835, "nsub" to 8836, "sube" to 8838, "supe" to 8839, "oplus" to 8853,
            "otimes" to 8855, "perp" to 8869, "sdot" to 8901,
            "lceil" to 8968, "rceil" to 8969, "lfloor" to 8970, "rfloor" to 8971,
            "lang" to 9001, "rang" to 9002, "loz" to 9674,
            "spades" to 9824, "clubs" to 9827, "hearts" to 9829, "diams" to 9830,
            "OElig" to 338, "oelig" to 339, "Scaron" to 352, "scaron" to 353, "Yuml" to 376,
            "circ" to 710, "tilde" to 732,
            "ensp" to 8194, "emsp" to 8195, "thinsp" to 8201, "zwnj" to 8204, "zwj" to 8205,
            "lrm" to 8206, "rlm" to 8207, "ndash" to 8211, "mdash" to 8212,
            "lsquo" to 8216, "rsquo" to 8217, "sbquo" to 8218,
            "ldquo" to 8220, "rdquo" to 8221, "bdquo" to 8222,
            "dagger" to 8224, "Dagger" to 8225, "permil" to 8240,
            "lsaquo" to 8249, "rsaquo" to 8250, "euro" to 8364
        ).forEach { (name, code) -> put(name, code.toChar()) }
    }
}
