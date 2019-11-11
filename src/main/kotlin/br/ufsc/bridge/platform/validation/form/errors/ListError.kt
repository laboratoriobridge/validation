package br.ufsc.bridge.platform.validation.form.errors

interface ListError<T> : ValidationError {

    override val isValid: Boolean

    fun error(mensagem: String)

    fun itemError(index: Int): FormError<T>

}
