# Bookstore JPA

Este repositório contém uma aplicação de exemplo usando Spring Boot e Spring Data JPA. O objetivo é demonstrar como modelar um pequeno sistema de livraria, explorando relacionamentos de entidades (muitos‑para‑muitos, um‑para‑muitos e um‑para‑um), o uso de registros (record) como DTO (Data Transfer Object), camadas de serviço e repositório, e exposição de operações CRUD através de uma API REST.

---

## 🚀 Funcionalidades

- Criação, listagem e remoção de livros.
- Uso de entidades `Author`, `Book`, `Publisher` e `Review` para representar autores, livros, editoras e resumos, respectivamente.
- **Relacionamentos:**
  - **Author ↔ Book**: muitos-para-muitos via a tabela de junção `tb_book_author`.
  - **Publisher → Book**: um-para-muitos; uma editora pode ter vários livros.
  - **Book → Review**: um-para-um; cada livro possui uma resenha.
- DTO: `BookRecordDto` utiliza o recurso de record do Java para transportar dados de criação de livros.
- Camada de serviço (`BookService`) para orquestração de operações e isolamento da lógica de negócios.
- Camada de repositórios (`AuthorRepository`, `BookRepository`, `PublisherRepository`, `ReviewRepository`) estende `JpaRepository` para fornecer operações de persistência.
- API REST com endpoints para salvar, listar e deletar livros.

---

## 📂 Estrutura de Pacotes

```
src/main/java/com/springboot/jpa
 ├── controller      # Controladores REST (ex.: BookController)
 ├── dto             # Objetos de transferência (ex.: BookRecordDto)
 ├── entity          # Entidades JPA (Book, Author, Publisher, Review)
 ├── repository      # Interfaces JpaRepository para persistência
 ├── service         # Regras de negócio (BookService)
 └── JpaApplication  # Classe principal com @SpringBootApplication
```

---

## ⚙️ Pré-requisitos

- **Java 21**: a configuração do projeto define `java.version` como 21.
- **Maven**: o repositório fornece os scripts de wrapper (`mvnw` e `mvnw.cmd`), portanto não é necessário instalar Maven globalmente.
- **PostgreSQL**: a aplicação utiliza PostgreSQL. Certifique‑se de ter uma instância rodando localmente e crie um banco de dados chamado `springboot-jpa`. O arquivo `application.properties` define a URL do banco (`jdbc:postgresql://localhost:5432/springboot-jpa`), usuário `postgres` e senha `root`; ajuste esses valores conforme o seu ambiente.

---

## 🔧 Instalação e execução

1. **Clone este repositório:**

   ```bash
   git clone https://github.com/MTaier/Bookstore-JPA.git
   cd Bookstore-JPA
   ```

2. **Configure o banco de dados:**

   - Crie o banco `springboot-jpa` no seu servidor PostgreSQL.
   - Ajuste o usuário e a senha no arquivo `src/main/resources/application.properties`, se necessário.
   - O Spring JPA está configurado para criar/atualizar as tabelas automaticamente  
     (`spring.jpa.hibernate.ddl-auto=update`).

3. **Compile e execute:**

   - No **Linux/Mac**:
   
     ```bash
     ./mvnw clean spring-boot:run
     ```

   - No **Windows**:
   
     ```bash
     mvnw.cmd clean spring-boot:run
     ```

     A aplicação iniciará em `http://localhost:8080`.

---

## 📡 Endpoints REST

A API REST é exposta através do controlador `BookController` em `/bookstore/books`.  
As principais rotas são:

| Verbo/rota             | Descrição                                                                                                                                          |
|-------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST** `/bookstore/books`   | Cria um novo livro. Recebe um JSON com `title`, `publisherId`, `authorsIds` (lista de UUIDs) e `reviewComment`. Retorna o livro criado com status **201**. |
| **GET** `/bookstore/books`    | Lista todos os livros cadastrados. Retorna um array de objetos `Book` com status **200**.                                                    |
| **DELETE** `/bookstore/books/{id}` | Remove o livro com o ID fornecido (UUID). Retorna status **204** quando a exclusão é bem-sucedida.                                        |

### Exemplo de requisição de criação

```http
POST /bookstore/books HTTP/1.1
Content-Type: application/json

{
  "title": "Spring Boot em Ação",
  "publisherId": "c0ae8151-a1b7-45e1-8edf-123456789abc",
  "authorsIds": ["7b981351-3266-4f1a-994f-987654321def"],
  "reviewComment": "Excelente leitura!"
}
```

A resposta conterá o livro salvo, incluindo o ID gerado e os dados associados.

---

## 📦 Pacotes e organização

- `controller`: expõe os endpoints REST (`BookController`).
- `controller/dto`: contém o DTO `BookRecordDto` para criação de livros.
- `entity`: define as entidades JPA (`Author`, `Book`, `Publisher`, `Review`).
- `repository`: interfaces JPA responsáveis pela persistência de cada entidade.
- `service`: contém `BookService`, que centraliza a lógica de negócio para livros.
- `resources`: arquivos de configuração (por exemplo, `application.properties`).
