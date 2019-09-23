package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;

public class Rules {

	public static final AlfaNumericoRule alfaNumerico = new AlfaNumericoRule();
	public static final BeforeEqualTodayRule beforeEqualToday = new BeforeEqualTodayRule();
	public static final BeforeTodayRule beforeToday = new BeforeTodayRule();
	public static final CepRule cep = new CepRule();
	public static final CnpjRule cnpj = new CnpjRule();
	public static final CnsRule cns = new CnsRule();
	public static final CpfRule cpf = new CpfRule();
	public static final EmailRule email = new EmailRule();
	public static final EmptyRule empty = new EmptyRule();
	public static final HoraRule hour = new HoraRule();
	public static final RequiredRule required = new RequiredRule();
	public static final TelefoneRule phone = new TelefoneRule();

	private Rules() {
		// nada a fazer
	}

	public static Rule<String> length(int length) {
		return new LengthRule(length);
	}

	public static Rule<String> maxLength(int maxLength) {
		return new MaxLengthRule(maxLength);
	}

	public static Rule<String> minLength(int minLength) {
		return new MinLengthRule(minLength);
	}

	public static <T extends Number> Rule<T> range(T minRange, T maxRange) {
		return new RangeRule<>(minRange, maxRange);
	}

	public static <T extends Number> Rule<T> maxRange(T maxRange) {
		return new MaxRangeRule<>(maxRange);
	}

	public static <T extends Number> Rule<T> minRange(T minRange) {
		return new MinRangeRule<>(minRange);
	}

	public static Rule<String> match(String regex) {
		return new MatchRule(regex);
	}
}
