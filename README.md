# Automação de Testes de API – Books

Este projeto foi desenvolvido como parte de um **desafio técnico de automação de testes de API**, com o objetivo de validar as operações de CRUD de uma API REST pública.

Embora o endpoint utilize o termo **Books**, neste contexto ele representa um **cadastro base de itens ou produtos**, sendo utilizado como modelo genérico para estudos de automação de APIs REST.

---

## Objetivo do Projeto

Garantir que o endpoint `/api/v1/Books` esteja funcionando corretamente por meio da validação de:

- Operações de Create, Read, Update e Delete
- Códigos de status HTTP
- Cenários positivos e negativos
- Boas práticas de automação e organização de código

---

## API Utilizada

- Base URL: `https://fakerestapi.azurewebsites.net`
- Endpoint: `/api/v1/Books`
- Tipo: API pública (FakeRestAPI)

---

## Tecnologias Utilizadas

### Java 21
Versão LTS mais recente da linguagem Java, trazendo melhorias de performance, segurança e clareza de código.

Benefícios:
- Código moderno e enxuto
- Base sólida para automação de testes
- Aderente aos padrões atuais do mercado

---

### Maven
Ferramenta de gerenciamento de dependências e automação de build.

Utilizado para:
- Gerenciar dependências do projeto
- Executar o ciclo de vida dos testes
- Padronizar a estrutura do projeto

---

### JUnit 5
Framework moderno para escrita e execução de testes automatizados.

Utilizado para:
- Organização dos cenários de teste
- Execução automatizada
- Melhor legibilidade e manutenção

---

### Rest Assured
Biblioteca específica para automação de testes de APIs REST.

Principais benefícios:
- Escrita fluente com `given / when / then`
- Validação simples de respostas JSON
- Integração direta com JUnit 5

---

### Lombok
Biblioteca utilizada para reduzir código repetitivo (boilerplate).

Benefícios:
- Código mais limpo e legível
- Menos getters, setters e construtores manuais
- Facilidade de manutenção

---

### Java Faker
Biblioteca para geração de dados dinâmicos e realistas para testes.

Benefícios:
- Evita dados estáticos
- Torna os testes mais robustos
- Facilita a criação de cenários variados

---

## Arquitetura do Projeto

O projeto segue uma **Arquitetura em Camadas (Layered Architecture)**, aplicando o princípio de **Separação de Responsabilidades**, amplamente utilizado em projetos profissionais de automação de testes de API.

Cada camada possui um papel bem definido, facilitando a manutenção, evolução e escalabilidade do projeto.

---


---

##  Camada `config`

Camada responsável por **centralizar as configurações da API**.

### Contém:
- URL base da API
- Endpoints
- Configurações reutilizáveis

### Benefícios:
- Evita duplicação de URLs
- Facilita mudanças de ambiente (dev, homologação, produção)
- Mantém configurações desacopladas da lógica de teste

---

##  Camada `client`

Camada responsável por **executar as chamadas HTTP da API**.

### Responsabilidades:
- Executar requisições:
    - `GET`
    - `POST`
    - `PUT`
    - `DELETE`
- Retornar o objeto `Response`
- **Não realizar validações de teste**

Essa camada atua apenas como intermediária entre os testes e a API.

### Benefícios:
- Centraliza a comunicação com a API
- Reduz acoplamento
- Mantém os testes limpos e focados

---

##  Camada `model`

Camada responsável por representar os **objetos da API (POJOs)**.

### Benefícios:
- Facilita serialização e desserialização JSON
- Evita criação manual de payloads
- Melhora a legibilidade dos testes
- Mantém alinhamento com o contrato da API

---

##  Camada `factory`

Camada responsável pela **criação de dados de teste**.

### Utilizada para:
- Criar payloads válidos
- Gerar variações de dados para diferentes cenários

### Benefícios:
- Padronização dos dados de teste
- Redução de código duplicado
- Facilidade de manutenção quando o contrato da API muda

---

##  Camada `tests`

Camada onde estão definidos os **cenários de teste**.

### Responsável por validar:
- Status codes
- Estrutura da resposta
- Comportamento dos endpoints

### Importante:
- ❌ Os testes **não fazem chamadas diretas à API**
- ✅ Toda comunicação ocorre exclusivamente pela camada `client`

---

##  Observação Importante – APIs Fake / Mockadas

Durante os testes foi identificado que o endpoint de **UPDATE (PUT)**:

- Retorna `status 200 OK`
- **Não persiste** os dados enviados no `body`

Esse comportamento é **esperado em APIs fake ou mockadas** e **não caracteriza falha na automação**.

### Nesses casos, os testes devem validar:
- Status code
- Estrutura da resposta
- Aceitação do request

 **Validação de persistência não se aplica** a esse tipo de API.


## Comportamento de PUT e DELETE com Payload `null` ou Recurso Inexistente

Durante a execução dos cenários negativos, foi observado que os endpoints de **PUT** e **DELETE** apresentam comportamentos específicos devido à natureza **fake/mockada** da API.

---

### PUT (Update) com dados inválidos ou recurso inexistente — API Fake

- O endpoint aceita payloads incompletos ou inválidos (inclusive `null`)
- Retorna **status 200 OK**
- Não realiza validações de regra de negócio
- Não persiste alterações

Esse comportamento é esperado em APIs Fake, cujo objetivo é **simular a existência do endpoint**, e não validar regras reais de domínio.

 **Abordagem de teste adotada:**
- O cenário é classificado como **negativo do ponto de vista de qualidade**
- A validação foca na **robustez do endpoint** e na **aceitação do request**
- Não é realizada validação de persistência ou consistência de dados

---

### DELETE de recurso inexistente ou inválido — API Fake

- O endpoint aceita a requisição mesmo quando o recurso não existe
- Retorna **status 200 OK**
- Não há validação real de existência do registro
- Não há erro ou exceção retornada

 **Abordagem de teste adotada:**
- O teste valida apenas o **comportamento do endpoint**
- O retorno `200 OK` é considerado aceitável dentro do contexto de uma API Fake
- O cenário é documentado como **negativo contextual**, e não como falha da automação

---

## Como deveria ser o comportamento em uma API real

Em uma **API real**, com persistência de dados e regras de negócio implementadas, os comportamentos esperados seriam diferentes.

### PUT (Update) — API Real

- `400 Bad Request`  
  Quando o payload é inválido, incompleto ou contém valores `null` não permitidos.

- `404 Not Found`  
  Quando o recurso informado para atualização não existe.

- `200 OK` ou `204 No Content`  
  Apenas quando a atualização ocorre com sucesso e os dados são persistidos corretamente.

---

### DELETE — API Real

- `404 Not Found`  
  Quando o recurso informado para exclusão não existe.

- `204 No Content`  
  Quando o recurso é removido com sucesso.

- `400 Bad Request`  
  Quando o identificador informado é inválido.

---

### Consideração Final sobre APIs Fake

Como se trata de uma **API Fake**, essas validações **não estão implementadas**.  
Por esse motivo, os testes deste projeto são ajustados para:

- Evitar falsos negativos
- Validar corretamente o **comportamento esperado da API**
- Demonstrar **entendimento de contexto**, e não apenas validação técnica

Esse cuidado reforça um ponto essencial em qualidade de software:

> **Testar não é apenas validar respostas, mas compreender o propósito do sistema testado.**



        

---

## ✅ Considerações Finais

Essa arquitetura garante:
- Separação clara de responsabilidades
- Testes mais limpos e profissionais
- Facilidade de manutenção
- Aderência às boas práticas de QA e automação de testes de API


## Estrutura do Projeto

```text
 src
└── test
    └── java
        └── com.qa.api
            ├── config
            │   └── ApiConfig.java
            ├── client
            │   └── BookClient.java
            ├── model
            │   └── Book.java
            ├── factory
            │   └── BookFactory.java
            ├── utils
            └── tests
                └── BookTest.java
