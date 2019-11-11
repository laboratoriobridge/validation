package br.ufsc.bridge.platform.validation.form.errors

import br.ufsc.bridge.metafy.MetaField
import br.ufsc.bridge.metafy.MetaList
import br.ufsc.bridge.platform.validation.engine.Rule
import java.util.function.BiConsumer
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

interface FormError<T> : ValidationError {

    override val isValid: Boolean

    fun isValid(field: MetaField<*>): Boolean

    fun <R> isValid(property: KProperty1<T, R>): Boolean

    fun fieldError(field: MetaField<*>, mensagem: String?)

    fun fieldError(property: KProperty<*>, mensagem: String?)

    fun error(mensagem: String?)

    fun <E> formError(field: MetaField<E>): FormError<E>

    fun <R> formError(property: KProperty1<T, R>): FormError<R>

    fun <E> listError(field: MetaList<E>): ListError<E>

    fun <R : List<E>?, E> listError(property: KProperty1<T, R>): ListError<E>

    fun <F> check(field: MetaField<F>, rule: Rule<F>): FormError<T>

    fun <R> check(property: KProperty1<T, R>, rule: Rule<R>): FormError<T>

    fun <E> forEach(field: MetaList<E>, itemValidator: BiConsumer<E, FormError<E>>): FormError<T>

    fun <R : List<E>?, E : Any?> forEach(property: KProperty1<T, R>, itemValidator: (E, FormError<E>) -> Unit): FormError<T>

}
