package br.ufsc.bridge.platform.validation.form.errors

import br.ufsc.bridge.platform.validation.rules.Rules
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class KFormErrorImplTest {

    @Test
    fun geral() {
        val form = Form()
        form.required = "1"

        val subform = SubForm()
        subform.nome = "José"
        form.list = listOf(subform)

        val errors = FormErrorImpl(form)

        errors
                .check(Form::cep, Rules.cep)
                .check(Form::cnpj, Rules.cnpj)
                .check(Form::cns, Rules.cns)
                .check(Form::cpf, Rules.cpf)
                .check(Form::email, Rules.email)
                .check(Form::hora, Rules.hour)
                .check(Form::maxDate, Rules.beforeToday)
                .check(Form::required, Rules.required)
                .check(Form::telefone, Rules.phone)
                .forEach(Form::list) { item, itemError -> itemError.check(SubForm::nome, Rules.required) }

        Assert.assertTrue(errors.isValid)
    }

    @Test
    fun invalido() {
        val form = Form()
        form.list = listOf(SubForm())

        val errors = FormErrorImpl(form)

        errors
                .check(Form::cep, Rules.cep)
                .check(Form::cnpj, Rules.cnpj)
                .check(Form::cns, Rules.cns)
                .check(Form::cpf, Rules.cpf)
                .check(Form::email, Rules.email)
                .check(Form::hora, Rules.hour)
                .check(Form::maxDate, Rules.beforeToday)
                .check(Form::required, Rules.required)
                .check(Form::telefone, Rules.phone)
                .forEach(Form::list) { item, itemError -> itemError.check(SubForm::nome, Rules.required) }

        Assert.assertFalse(errors.isValid(Form::list))
        Assert.assertFalse(errors.isValid)
    }

    @Test
    fun desconsiderarMensagensNulas() {
        val form = Form()

        val errors = FormErrorImpl(form)

        errors.fieldError(Form::cep, null)

        Assert.assertTrue(errors.isValid)
    }

    @Test
    fun formRootError() {
        val form = Form()

        val errors = FormErrorImpl(form)

        errors.error("Objeto inválido")

        Assert.assertFalse(errors.isValid)
        Assert.assertEquals("Objeto inválido", errors.errors)
    }

    @Test
    fun subFormRootError() {
        val form = Form()

        val errors = FormErrorImpl(form)

        errors.formError(Form::sub).error("Objeto inválido")

        errors.error("Objeto inválido")

        Assert.assertFalse(errors.isValid)
        Assert.assertEquals("Objeto inválido", errors.errors)

        errors.error(null)

        Assert.assertFalse(errors.isValid)
        Assert.assertEquals("Objeto inválido", (errors.errors as Map<*, *>)[Form::sub.name])
    }

    @Test
    fun listRootError() {
        val form = Form()

        val errors = FormErrorImpl(form)

        errors.listError(Form::list).error("Objeto inválido")

        Assert.assertFalse(errors.isValid)
        Assert.assertEquals("Objeto inválido", (errors.errors as Map<*, *>)[Form::list.name])
    }

    @Test
    fun nullForm() {
        val errors = FormErrorImpl<Form>(null)

        errors.check(Form::nome, Rules.required)

        Assert.assertFalse(errors.isValid)
    }

    @Test
    fun multipleRules() {
        val errors = FormErrorImpl<Form>(null)

        errors
                .check(Form::cpf, Rules.required)
                .check(Form::cpf, Rules.cpf)

        Assert.assertFalse(errors.isValid)
    }

    class Form {
        var cep: String? = null
        var cnpj: String? = null
        var cns: String? = null
        var cpf: String? = null
        var dataNascimento: LocalDate? = null
        var duracao: String? = null
        var email: String? = null
        var hora: String? = null
        var maxDate: LocalDate? = null
        var nome: String? = null
        var required: String? = null
        var telefone: String? = null
        var list: List<SubForm>? = null
        var sub: SubForm? = null
    }

    class SubForm {
        var nome: String? = null
    }
}