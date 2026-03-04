# 📚 API Books

API REST desenvolvida em **Spring Boot** para gerenciamento de livros.

Projeto acadêmico do 4º semestre com foco em arquitetura REST, organização em camadas e boas práticas com Spring.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (ambiente de testes)
- Maven
- Lombok

---

## 📂 Estrutura do Projeto
src
├── controller
├── service
├── repository
├── entities (ou model)
└── BooksApplication.java

Arquitetura em camadas:

- **Controller** → Responsável pelas requisições HTTP
- **Service** → Regras de negócio
- **Repository** → Acesso ao banco de dados
- **Entity** → Representação da tabela no banco

---

## 📌 Endpoints Disponíveis

### 🔍 Listar livros

http://localhost:8080/h2-console

Rafael Alvarenga
