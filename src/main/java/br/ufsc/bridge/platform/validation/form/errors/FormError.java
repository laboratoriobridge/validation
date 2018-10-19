package br.ufsc.bridge.platform.validation.form.errors;

import java.time.LocalDate;
import java.util.function.BiConsumer;

import br.ufsc.bridge.metafy.MetaField;
import br.ufsc.bridge.metafy.MetaList;
import br.ufsc.bridge.platform.validation.engine.Rule;

public interface FormError extends ValidationError {

	@Override
	boolean isValid();

	boolean isValid(MetaField<?> field);

	void fieldError(MetaField<?> field, String mensagem);

	FormError formError(MetaField<?> field);

	ListError listError(MetaList<?> field);

	<F> FormError check(MetaField<F> field, Rule<F> rule);

	@Deprecated
	void alfaNumerico(MetaField<String> field);

	@Deprecated
	void beforeToday(MetaField<LocalDate> field);

	@Deprecated
	void cep(MetaField<String> field);

	@Deprecated
	void cpf(MetaField<String> field);

	@Deprecated
	void cnpj(MetaField<String> field);

	@Deprecated
	void cns(MetaField<String> field);

	@Deprecated
	void email(MetaField<String> field);

	@Deprecated
	void empty(MetaField<?> field);

	@Deprecated
	void hour(MetaField<String> field);

	@Deprecated
	void length(MetaField<String> field, int length);

	@Deprecated
	void match(MetaField<String> field, String regex);

	@Deprecated
	void maxLength(MetaField<String> field, int length);

	@Deprecated
	<T extends Number> void maxRange(MetaField<T> field, T maxRange);

	@Deprecated
	void minLength(MetaField<String> field, int minLength);

	@Deprecated
	<T extends Number> void minRange(MetaField<T> field, T minRange);

	@Deprecated
	<T extends Number> void range(MetaField<T> field, T minRange, T maxRange);

	@Deprecated
	void required(MetaField<?> field);

	@Deprecated
	void phone(MetaField<String> field);

	<T> FormError forEach(MetaList<T> field, BiConsumer<T, FormError> itemValidator);

}
