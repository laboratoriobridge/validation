package br.ufsc.bridge.platform.validation.form.errors;

import java.time.LocalDate;

import br.ufsc.bridge.metafy.MetaField;
import br.ufsc.bridge.metafy.MetaList;

public interface FormError extends ValidationError {

	boolean isValid();

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	FormError formError(String form);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	ListError listError(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void fieldError(String campo, String titulo, String mensagem);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void fieldError(String campo, FieldError error);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void alfaNumerico(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void cep(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void cnpj(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void cns(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void cpf(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void dataNasc(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void dataMax(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void duracao(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void email(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void hora(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void length(String campo, Number length);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void maxLength(String campo, Number maxLength);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void maxRange(String campo, Number maxRange);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void minLength(String campo, Number minLength);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void minRange(String campo, Number minRange);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void nome(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void range(String campo, Number minRange, Number maxRange);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void registroAnvisa(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void required(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void senha(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	void telefone(String campo);

	/**
	 * Usar o equivalente com MetaField.
	 */
	@Deprecated
	boolean fieldIsValid(String form);

	boolean fieldIsValid(MetaField<?> field);

	void fieldError(MetaField<?> field, String titulo, String mensagem);

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

	void maxRange(MetaField<Number> field, Number maxRange);

	void minLength(MetaField<String> field, Number minLength);

	void minRange(MetaField<Number> field, Number minRange);

	void nome(MetaField<String> field);

	void range(MetaField<Number> field, Number minRange, Number maxRange);

	void registroAnvisa(MetaField<String> field);

	void required(MetaField<?> field);

	void senha(MetaField<String> field);

	void telefone(MetaField<String> field);

}
