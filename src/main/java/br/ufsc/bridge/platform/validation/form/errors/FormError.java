package br.ufsc.bridge.platform.validation.form.errors;

import java.util.function.BiConsumer;

import org.joda.time.LocalDate;

import br.ufsc.bridge.metafy.MetaField;
import br.ufsc.bridge.metafy.MetaList;
import br.ufsc.bridge.platform.validation.engine.Rule;

public interface FormError extends ValidationError {

	@Override
	boolean isValid();

	boolean fieldIsValid(MetaField<?> field);

	void fieldError(MetaField<?> field, String mensagem);

	FormError formError(MetaField<?> field);

	ListError listError(MetaList<?> field);

	<F> void checkRule(MetaField<F> field, Rule<F> rule);

	void alfaNumerico(MetaField<String> field);

	void beforeToday(MetaField<LocalDate> field);

	void cep(MetaField<String> field);

	void cpf(MetaField<String> field);

	void cnpj(MetaField<String> field);

	void cns(MetaField<String> field);

	void email(MetaField<String> field);

	void empty(MetaField<?> field);

	void hora(MetaField<String> field);

	void length(MetaField<String> field, int length);

	void match(MetaField<String> field, String regex);

	void maxLength(MetaField<String> field, int length);

	<T extends Number> void maxRange(MetaField<T> field, T maxRange);

	void minLength(MetaField<String> field, int minLength);

	<T extends Number> void minRange(MetaField<T> field, T minRange);

	<T extends Number> void range(MetaField<T> field, T minRange, T maxRange);

	void required(MetaField<?> field);

	void telefone(MetaField<String> field);

	<T> void validateList(MetaList<T> field, BiConsumer<T, FormError> itemValidator);

}
