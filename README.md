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
