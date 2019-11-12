package br.ufsc.bridge.platform.validation.form.errors

import java.io.Serializable

interface ValidationError : Serializable {

    val isValid: Boolean

    val errors: Any

}
