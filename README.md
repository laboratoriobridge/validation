# RNI Validation

Validadores compartilhados entre o server e o client side do RNI.

As regras de validação e validadores são definidos no javascript e utilizados no Java via Nashorn, para evitar duplicação das regras de validação no cliente e no servidor.

## Como usar (Client-Side)

```js
import * as validation from 'validation-rules';

let result = validation.validate("Carlos", [validation.Required, validation.Nome]); // retorna o primeiro erro de validação
```

## Como usar (Server-Side)

Utilizando as anotações das regras de validação. Exemplo:

```java
public static class ValidateMe {
  @Nome
  String nome;
}
```

# Criando novas regras / validadores

1. Crie um arquivo javascript contendo a nova regra ou validador em `src/main/script/rules`, por exemplo `NovaRegra.js`.
1. Crie uma anotação no Java com o mesmo nome da regra no pacote pertinente.
1. Crie um validador no Java com o nome `NovaRegraValidator` extendendo `JavascriptValidator`.
1. Crie testes unitários para a regra de validação, no Java, utilizando jUnit.
