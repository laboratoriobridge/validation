package br.ufsc.bridge.platform.validation.rules;

import br.ufsc.bridge.platform.validation.engine.Rule;

public class Rules {

	public static final AlfaNumericoRule alfaNumerico = new AlfaNumericoRule();
	public static final CepRule cep = new CepRule();
	public static final CnpjRule cnpj = new CnpjRule();
	public static final CnsRule cns = new CnsRule();
	public static final CpfRule cpf = new CpfRule();
	public static final DataNascimentoRule dataNascimento = new DataNascimentoRule();
	public static final DuracaoRule duracao = new DuracaoRule();
	public static final EmailRule email = new EmailRule();
	public static final EmptyRule empty = new EmptyRule();
	public static final HoraRule hora = new HoraRule();
	public static final MaxDateRule maxDate = new MaxDateRule();
	public static final NomeRule nome = new NomeRule();
	public static final RequiredRule required = new RequiredRule();
	public static final SenhaRule senha = new SenhaRule();
	public static final TelefoneRule telefone = new TelefoneRule();

	private Rules() {
		// nada a fazer
	}

	public static Rule length(Number length) {
		return new LengthRule(length);
	}

	public static Rule maxLength(Number maxLength) {
		return new MaxLengthRule(maxLength);
	}

	public static Rule minLength(Number minLength) {
		return new MinLengthRule(minLength);
	}

	public static Rule range(Number minRange, Number maxRange) {
		return new RangeRule(minRange, maxRange);
	}

	public static Rule maxRange(Number maxRange) {
		return new MaxRangeRule(maxRange);
	}

	public static Rule minRange(Number minRange) {
		return new MinRangeRule(minRange);
	}

	public static Rule match(String regex) {
		return new MatchRule(regex);
	}
}
