package br.ufsc.bridge.platform.validation.form.errors

import br.ufsc.bridge.metafy.MetaField
import br.ufsc.bridge.metafy.MetaList
import br.ufsc.bridge.platform.validation.engine.Rule
import br.ufsc.bridge.platform.validation.util.Reflections
import java.util.function.BiConsumer
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

class FormErrorImpl<T> @JvmOverloads constructor(
        private val target: T? = null,
        private val internalErrors: HashMap<String, Any> = HashMap(),
        private var rootError: String? = null
) : FormError<T> {
    override val isValid: Boolean
        get() {
            var valid = this.rootError == null

            if (valid) {
                val iterator = this.internalErrors.values.iterator()
                while (iterator.hasNext()) {
                    val validationError = iterator.next()
                    valid = if (validationError is ValidationError) {
                        val childFormValid = validationError.isValid
                        if (childFormValid) {
                            iterator.remove()
                        }
                        valid && childFormValid
                    } else {
                        false
                    }
                }
            }

            return valid
        }

    override fun isValid(field: MetaField<*>): Boolean {
        var valid = true
        val error = this.internalErrors[field.alias]
        if (error is String) {
            valid = false
        } else if (error is ValidationError) {
            valid = error.isValid
        }
        return valid
    }

    override fun <R> isValid(property: KProperty1<T, R>): Boolean {
        var valid = true
        val error = this.internalErrors[property.name]
        if (error is String) {
            valid = false
        } else if (error is ValidationError) {
            valid = error.isValid
        }
        return valid
    }

    override fun fieldError(property: KProperty<*>, mensagem: String?) {
        if (mensagem != null) {
            this.internalErrors[property.name] = mensagem
        } else {
            this.internalErrors.remove(property.name)
        }
    }

    override fun fieldError(field: MetaField<*>, mensagem: String?) {
        if (mensagem != null) {
            this.internalErrors[field.alias] = mensagem
        } else {
            this.internalErrors.remove(field.alias)
        }
    }

    protected fun getFieldValue(field: MetaField<*>): Any? {
        return if (null != target) Reflections.getValue(this.target, field.alias) else null
    }

    protected fun <R> getPropertyValue(property: KProperty1<T, R>): R? {
        return if (null != target) property.call(target) else null
    }

    override fun error(mensagem: String?) {
        rootError = mensagem
    }

    override fun <E> formError(field: MetaField<E>): FormError<E> {
        return this.internalErrors.getOrPut(field.alias, { FormErrorImpl(getFieldValue(field)) }) as FormError<E>
    }

    override fun <E> formError(field: MetaField<E>, error: FormError<E>) {
        this.internalErrors.putIfAbsent(field.alias, error)
    }

    override fun <R> formError(property: KProperty1<T, R>): FormError<R> {
        return this.internalErrors.getOrPut(property.name, { FormErrorImpl(getPropertyValue(property)) }) as FormError<R>
    }

    override fun <R> formError(property: KProperty1<T, R?>, error: FormError<R>) {
        this.internalErrors.putIfAbsent(property.name, error)
    }

    override fun <E> listError(field: MetaList<E>): ListError<E> {
        return this.internalErrors.getOrPut(field.alias, { ListErrorImpl(getFieldValue(field) as List<E>?) }) as ListError<E>
    }

    override fun <R : List<E>?, E> listError(property: KProperty1<T, R>): ListError<E> {
        return this.internalErrors.getOrPut(property.name, { ListErrorImpl(getPropertyValue(property)) }) as ListError<E>
    }

    override fun <F : Any?> check(field: MetaField<F>, rule: Rule<F>): FormError<T> {
        if (isValid(field)) {
            val result = rule.validate(getFieldValue(field) as F?)
            fieldError(field, result)
        }
        return this
    }

    override fun <R> check(property: KProperty1<T, R>, rule: Rule<R>): FormError<T> {
        if (isValid(property)) {
            val result = rule.validate(getPropertyValue(property))
            fieldError(property, result)
        }
        return this
    }

    override fun <E : Any?> forEach(field: MetaList<E>, itemValidator: BiConsumer<E, FormError<E>>): FormError<T> {
        val list = getFieldValue(field) as List<*>?

        if (list == null || list.isEmpty()) {
            return this
        }

        val listErrors = this.listError(field)

        for (i in list.indices) {
            val item = list[i]
            itemValidator.accept(item as E, listErrors.itemError(i))
        }
        return this
    }

    override fun <R : List<E>?, E : Any?> forEach(property: KProperty1<T, R>, itemValidator: (E, FormError<E>) -> Unit): FormError<T> {
        val list = property.call(target)

        if (list == null || list.isEmpty()) {
            return this
        }

        val listErrors = this.listError(property)

        for (i in list.indices) {
            val item = list[i]
            itemValidator(item, listErrors.itemError(i))
        }
        return this
    }

    override val errors: Any
        get() {
            if (this.rootError != null) {
                return this.rootError as String
            } else {
                fun convertValue(value: Any): Any {
                    var convertedValue: Any = value
                    if (value is ValidationError) {
                        convertedValue = value.errors
                    }
                    return convertedValue
                }

                return this.internalErrors.entries.associateBy({ entry -> entry.key }, { entry -> convertValue(entry.value) })
            }
        }

}