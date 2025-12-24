#  Automação de Testes de API – Books

Este projeto foi desenvolvido como parte de um **desafio técnico de automação de testes de API**, com o objetivo de validar as operações de CRUD de uma API REST pública.

Embora o endpoint utilize o termo "Books", neste contexto ele representa o **cadastro base de itens/produtos**.

---

## Objetivo do Projeto

Garantir que o endpoint `/api/v1/Books` esteja funcionando corretamente, validando:

- Operações de **Create, Read, Update e Delete**
- Códigos de status HTTP
- Cenários positivos e negativos
- Estrutura limpa e boas práticas de automação

---

## API Utilizada

- Base URL: `https://fakerestapi.azurewebsites.net`
- Endpoint: `/api/v1/Books`
- Documentação: API pública FakeRest

---

##  Tecnologias Utilizadas

### Java 21
Java 21 é a versão LTS mais recente da linguagem, trazendo avanços significativos em performance, segurança e simplicidade.
- **Conceitos-chave:**
    - *Records* → estruturas imutáveis para representar dados de forma concisa.
    - *Sealed classes* → controle explícito de hierarquias de herança.
    - *Virtual Threads (Project Loom)* → concorrência leve e escalável, ideal para testes de carga e APIs.
- **Benefício:** Permite escrever código moderno, enxuto e altamente performático.

---

### Maven
Maven é um gerenciador de build e dependências baseado em um modelo declarativo (POM).
- **Conceitos-chave:**
    - *Ciclo de vida* → fases como `compile`, `test`, `package`, `deploy`.
    - *Gerenciamento de dependências* → resolve versões e conflitos automaticamente.
    - *Plugins* → estendem funcionalidades (ex.: Surefire para execução de testes).
- **Benefício:** Padroniza projetos Java e facilita integração contínua.

---

### JUnit 5
JUnit 5 é o framework de testes moderno e modular.
- **Conceitos-chave:**
    - *Arquitetura* → composta por Platform (execução), Jupiter (API moderna) e Vintage (compatibilidade).
    - *Extensibilidade* → permite interceptar execução, parametrizar testes e integrar com outras ferramentas.
    - *Anotações* → `@Test`, `@BeforeEach`, `@DisplayName`, `@ParameterizedTest`.
- **Benefício:** Testes mais legíveis, flexíveis e poderosos.

---

### Rest Assured
Rest Assured é uma DSL para testes de APIs REST.
- **Conceitos-chave:**
    - *Modelo fluente* → `given / when / then` para clareza na escrita dos testes.
    - *Validação de payloads* → uso de JSONPath e *Matchers* para verificar respostas.
    - *Integração* → funciona diretamente com JUnit 5 e frameworks de serialização (Jackson/Gson).
- **Benefício:** Simplifica a automação de testes de APIs com sintaxe expressiva.

---

### Lombok
Lombok é uma biblioteca que gera código repetitivo em tempo de compilação via *annotation processing*.
- **Conceitos-chave:**
    - *Anotações* → `@Data`, `@Builder`, `@Value`, `@AllArgsConstructor`.
    - *Boilerplate reduction* → elimina necessidade de escrever getters, setters e construtores manualmente.
    - *Integração IDE* → requer plugin para suporte às anotações.
- **Benefício:** Código mais limpo, legível e fácil de manter.

---

### Java Faker
Java Faker gera dados sintéticos realistas para testes.
- **Conceitos-chave:**
    - *Categorias* → nomes, e-mails, endereços, telefones, etc.
    - *Locales* → suporte a diferentes idiomas e formatos culturais.
    - *Seeds* → geração determinística para reprodutibilidade em testes.
- **Benefício:** Testes mais robustos e dinâmicos, evitando dados estáticos e repetitivos.

---

##  Estrutura do Projeto

