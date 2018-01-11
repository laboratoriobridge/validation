package br.ufsc.bridge.platform.validation.form.errors;

import org.joda.time.LocalDate;

import br.ufsc.bridge.metafy.MetaField;
import br.ufsc.bridge.metafy.MetaList;

public interface FormError extends ValidationError {

	boolean isValid();

	boolean fieldIsValid(MetaField<?> field);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void fieldError(String campo, String mensagem);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void fieldError(String campo, FieldError error);

	void fieldError(MetaField<?> field, String mensagem);

	void fieldError(MetaField<?> field, FieldError error);

	FormError formError(MetaField<?> field);

	ListError listError(MetaList<?> field);

	void alfaNumerico(MetaField<String> field);

	void cep(MetaField<String> field);

	void cpf(MetaField<String> field);

	void cnpj(MetaField<String> field);

	void cns(MetaField<String> field);

	void dataNasc(MetaField<LocalDate> field);

	void dataMax(MetaField<LocalDate> field);

	void duracao(MetaField<String> field);

	void email(MetaField<String> field);

	void hora(MetaField<String> field);

	void length(MetaField<String> field, Number length);

	void match(MetaField<String> field, String regex);

	void maxLength(MetaField<String> field, Number length);

	void maxRange(MetaField<? extends Number> field, Number maxRange);

	void minLength(MetaField<String> field, Number minLength);

	void minRange(MetaField<? extends Number> field, Number minRange);

	void nome(MetaField<String> field);

	void range(MetaField<? extends Number> field, Number minRange, Number maxRange);

	void required(MetaField<?> field);

	void senha(MetaField<String> field);

	void telefone(MetaField<String> field);

}
