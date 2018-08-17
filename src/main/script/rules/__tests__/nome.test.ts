import { nomeRule } from '../nome'

it('should not accept this', () => {
    expect(nomeRule('AAA BB')).toEqual(undefined)
    expect(nomeRule('AA BBB')).toEqual(undefined)
    expect(nomeRule('AA BB')).toEqual('Informe nome e sobrenome')
})
