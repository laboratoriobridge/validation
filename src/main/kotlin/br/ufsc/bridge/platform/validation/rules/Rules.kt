package br.ufsc.bridge.platform.validation.rules

import br.ufsc.bridge.platform.validation.engine.Rule

object Rules {
    @JvmField
    val alfaNumerico = AlfaNumericoRule()
    @JvmField
    val beforeEqualToday = BeforeEqualTodayRule()
    @JvmField
    val beforeToday = BeforeTodayRule()
    @JvmField
    val cep = CepRule()
    @JvmField
    val cnpj = CnpjRule()
    @JvmField
    val cns = CnsRule()
    @JvmField
    val cpf = CpfRule()
    @JvmField
    val email = EmailRule()
    @JvmField
    val empty = EmptyRule()
    @JvmField
    val hour = HoraRule()
    @JvmField
    val required = RequiredRule()
    @JvmField
    val phone = TelefoneRule()

    @JvmStatic
    fun length(length: Int): Rule<String?> {
        return LengthRule(length)
    }

    @JvmStatic
    fun maxLength(maxLength: Int): Rule<String?> {
        return MaxLengthRule(maxLength)
    }

    @JvmStatic
    fun minLength(minLength: Int): Rule<String?> {
        return MinLengthRule(minLength)
    }

    @JvmStatic
    fun range(minRange: Number, maxRange: Number): Rule<Number?> {
        return RangeRule(minRange, maxRange)
    }

    @JvmStatic
    fun maxRange(maxRange: Number): Rule<Number?> {
        return MaxRangeRule(maxRange)
    }

    @JvmStatic
    fun minRange(minRange: Number): Rule<Number?> {
        return MinRangeRule(minRange)
    }

    @JvmStatic
    fun match(regex: String): Rule<String?> {
        return MatchRule(regex)
    }

    @JvmStatic
    fun richTextMaxLength(maxLength: Int): RichTextMaxLengthRule {
        return RichTextMaxLengthRule(maxLength)
    }
}
