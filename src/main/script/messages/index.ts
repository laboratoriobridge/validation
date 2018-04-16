import { messages as en } from './locales/en'
import { messages as ptBr } from './locales/pt-br'

export type Locale = 'en' | 'pt-br'

export type MessageKey =
    | 'cep'
    | 'cnpj'
    | 'cns'
    | 'cpf'
    | 'dataNasc'
    | 'maxDate'
    | 'duracao'
    | 'email'
    | 'empty'
    | 'hora'
    | 'length'
    | 'minLength'
    | 'maxLength'
    | 'match'
    | 'nome'
    | 'range'
    | 'minRange'
    | 'maxRange'
    | 'required'
    | 'senha'
    | 'telefone'

export type ValidationMessages = { [key in MessageKey]: (value: any, ...args: any[]) => string }

export const localeMessages: { [key in Locale]: ValidationMessages } = {
    'en': en,
    'pt-br': ptBr,
}

let __VALIDATION_LOCALE_DEFAULT: Locale = 'pt-br'

export const setLocale = (locale: Locale) => {
    __VALIDATION_LOCALE_DEFAULT = locale
}

export const msg = (key: MessageKey, value: any, ...args: any[]): string =>
    msgLocale(__VALIDATION_LOCALE_DEFAULT, key, value, ...args)

export const msgLocale = (locale: Locale, key: MessageKey, value: any, ...args: any[]): string =>
    localeMessages[locale][key](value, ...args)
