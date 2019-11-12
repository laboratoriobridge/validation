package br.ufsc.bridge.platform.validation.form.errors

import java.util.*

class ListErrorImpl<T>(private val target: List<T>?) : ListError<T> {
    private var rootError: String? = null
    private val itemErrors = ArrayList<FormError<T>>()

    override val isValid: Boolean
        get() {
            var valid = this.rootError == null

            if (valid) {
                val iterator = this.itemErrors.iterator()
                while (iterator.hasNext()) {
                    val validationError = iterator.next()
                    valid = if (validationError is FormErrorImpl<*>) {
                        val childFormValid = validationError.isValid
                        valid && childFormValid
                    } else {
                        false
                    }
                }
            }

            return valid
        }

    override val errors: Any
        get() =
            if (this.rootError != null) {
                this.rootError as String
            } else {
                itemErrors
                        .map { { it.errors } }
                        .toList()
            }


    override fun error(mensagem: String) {
        this.rootError = mensagem
    }

    override fun itemError(index: Int): FormErrorImpl<T> {
        if (this.itemErrors.size > index) {
            return this.itemErrors[index] as FormErrorImpl<T>
        }
        val itemError = FormErrorImpl(this.target?.get(index))
        this.itemErrors.add(index, itemError)
        return itemError
    }

}
