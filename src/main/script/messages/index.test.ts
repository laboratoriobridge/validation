import { msg, msgLocale, setLocale } from './'

describe('msgLocale', () => {
    it('should return the right message according to locale', () => {
        expect(msgLocale('pt-br', 'required', null)).toEqual('Campo é de preenchimento obrigatório')
        expect(msgLocale('en', 'required', null)).toEqual('Required')
    })
    it('should pass down the value and rule arguments', () => {
        expect(msgLocale('pt-br', 'length', 10, 30)).toEqual('Campo deve possuir 30 caracteres')
    })
})

describe('msg', () => {
    it('should return the message from default locale', () => {
        expect(msg('required', null)).toEqual('Campo é de preenchimento obrigatório')
    })
    it('should pass down the value and rule arguments', () => {
        expect(msg('length', 12, 40)).toEqual('Campo deve possuir 40 caracteres')
    })
})

describe('setLocale', () => {
    it('should change the default locale', () => {
        expect(msg('required', null)).toEqual('Campo é de preenchimento obrigatório')

        setLocale('en')
        expect(msg('required', null)).toEqual('Required')

        setLocale('pt-br')
        expect(msg('required', null)).toEqual('Campo é de preenchimento obrigatório')
    })
})
