# Desafio Full-Stack

## Descrição do Projeto

Este projeto foi desenvolvido como parte de um desafio para avaliar habilidades de desenvolvimento Full-Stack. O objetivo é implementar um sistema que permita gerenciar **empresas** e **fornecedores**, atendendo aos requisitos funcionais e regras de negócio especificados.

---

## Tecnologias Utilizadas

- **Back-end**:
  - Java 17
  - Spring Boot
  - PostgreSQL
  - Lombok
  - JUnit
  - Mockito
- **Front-end**:
  - Angular 18
  - TypeScript
  - HTML e CSS
  - Angular Material
- **Outros**:
  - Git
  - Docker

---

## Funcionalidades Implementadas

- CRUD completo para **Empresas** e **Fornecedores**.
- Relacionamento de muitos-para-muitos entre empresas e fornecedores.
- Regras de negócio:
  - Validação de CNPJ e CPF como valores únicos.
  - Cadastro de RG e data de nascimento para fornecedores pessoa física.
  - Restrições para fornecedores pessoa física menores de idade no estado do Paraná.
- Filtros na listagem de fornecedores por Nome e CPF/CNPJ.
- Validação de CEP utilizando a API externa [http://cep.la/api](http://cep.la/api), tanto no back-end quanto no front-end.
- Estrutura modular e extensível para adição de novos recursos.
- Testes unitários (opcional).
- Configuração de Docker para simplificar a execução do projeto (opcional).

---

## Estrutura do Back-End

A estrutura do back-end foi organizada de maneira modular para facilitar a manutenção e o entendimento do código:

- **`config`**: Configurações gerais do projeto, como CORS, autenticação, e conexão com banco de dados.
- **`constant`**: Definição de constantes usadas em toda a aplicação.
- **`controller`**: Contém os endpoints REST que expõem os serviços do sistema.
- **`service`**: Implementações das regras de negócio e lógica de aplicação.
- **`entity`**: Definição das entidades do sistema, mapeadas para o banco de dados.
- **`exception`**: Gerenciamento de exceções customizadas.
- **`repository`**: Interfaces para acesso ao banco de dados utilizando Spring Data JPA.
- **`test`**: Testes unitários com JUnit e Mockito.

---

## Estrutura do Front-End

O front-end foi desenvolvido com Angular, utilizando uma organização baseada em boas práticas para projetos modulares:

- **`environment`**: Configurações de ambiente (ex.: URLs de APIs para diferentes ambientes como desenvolvimento e produção).
- **`pages`**: Componentes principais das páginas da aplicação, como a listagem de fornecedores ou o cadastro de empresas.
- **`shared`**: Componentes, pipes e serviços reutilizáveis em toda a aplicação (ex.: validação de CEP, botões, tabelas).

---

## Como Executar o Projeto

### Pré-requisitos

- **Docker** instalado
- **Java 17** configurado no ambiente
- **Node.js** (versão recomendada para Angular 18)

### Passos para Executar

1. **Back-end**:
   - Compile e execute a aplicação com:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Alternativamente, use o Docker:
     ```bash
     docker build -t desafio-backend .
     docker run -p 8080:8080 desafio-backend
     ```

2. **Front-end**:
   - Instale as dependências:
     ```bash
     npm install
     ```
   - Execute a aplicação:
     ```bash
     ng serve
     ```
   - Acesse em [http://localhost:4200](http://localhost:4200).

3. **Banco de Dados**:
   - Utilize o container Docker configurado ou ajuste as configurações no `application.properties` para conectar ao PostgreSQL local.

---

## Melhorias Futuras

- Adicionar cobertura completa de testes unitários e de integração.
- Implementar autenticação e autorização para segurança.
- Expandir o front-end com design responsivo e melhorias de usabilidade.
- Criar documentação detalhada da API com Swagger ou Springdoc.
