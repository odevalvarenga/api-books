# 📚 API Books

API REST desenvolvida com **Spring Boot** para gerenciamento de livros, autores, categorias, editoras e avaliações.

Projeto acadêmico do 4º semestre com foco em:

* Arquitetura REST
* Boas práticas com Spring
* Organização em camadas (Controller, Service, Repository)

---

## 🚀 Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Banco de dados H2 (ambiente de desenvolvimento)
* Maven
* Lombok

---

## 📂 Estrutura do Projeto

```
src/
 ├── controllers
 ├── service
 ├── repository
 ├── entities
 ├── enums
 └── BooksApplication.java
```

### 🧱 Arquitetura em Camadas

* **Controller** → Recebe requisições HTTP
* **Service** → Contém regras de negócio
* **Repository** → Acesso ao banco de dados
* **Entity** → Representação das tabelas

---

## 📌 Entidades

A API possui as seguintes entidades:

* Author (Autor)
* Book (Livro)
* Category (Categoria)
* Publisher (Editora)
* Review (Avaliação)

---

## 🔗 Relacionamentos

* Book → ManyToOne → Author
* Book → ManyToOne → Category
* Book → ManyToOne → Publisher
* Book → OneToMany → Review

---

## 🌐 Endpoints Principais

### 📚 Books

| Método | Endpoint    | Descrição                |
| ------ | ----------- | ------------------------ |
| GET    | /books      | Listar livros (paginado) |
| POST   | /books      | Criar livro              |
| PUT    | /books/{id} | Atualizar livro          |
| DELETE | /books/{id} | Deletar livro            |

### ⭐ Reviews

| Método | Endpoint      |
| ------ | ------------- |
| GET    | /reviews      |
| POST   | /reviews      |
| PUT    | /reviews/{id} |
| DELETE | /reviews/{id} |

*(Demais entidades seguem o mesmo padrão CRUD)*

---

## 📄 Paginação

Exemplo de requisição:

```
GET /books?page=0&size=5
```

---

## 🧪 Testes da API

A coleção do Postman está disponível em:

```
/postman/books-api.postman_collection.json
```

### ▶️ Como usar:

1. Abra o Postman
2. Clique em **Import**
3. Selecione o arquivo da pasta `postman`
4. Execute os endpoints

---

## 🗄️ Banco de Dados H2

Acesse:

```
http://localhost:8080/h2-console
```

### Configuração padrão:

* JDBC URL: `jdbc:h2:mem:testdb`
* User: `sa`
* Password: (vazio)

---

## ▶️ Como Executar o Projeto

```bash
mvn spring-boot:run
```

Ou execute a classe:

```
BooksApplication.java
```

---

## 📌 Status do Projeto

✅ CRUD completo
✅ Paginação implementada
✅ Relacionamentos entre entidades
✅ Validações com Bean Validation
✅ Testes via Postman

---

## 👨‍💻 Autor

Rafael Alvarenga

