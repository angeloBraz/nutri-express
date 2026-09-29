# Nutri Express - API REST

Esta é a API REST do sistema **Nutri Express**, desenvolvida como atividade prática para gerenciamento do cardápio de um aplicativo de delivery de comida saudável.

## 🚀 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot** (Web, Data JPA, Validation)
- **PostgreSQL**
- **Maven**

## 📋 Funcionalidades Implementadas
O projeto atende a todos os critérios estabelecidos na atividade, incluindo os desafios extras (bônus):
- **CRUD Completo:** Criação, leitura, atualização e remoção de pratos.
- **Arquitetura em Camadas:** Separação clara entre `Controller`, `Service` e `Repository`.
- **Uso de DTOs:** Transferência de dados feita exclusivamente via `PratoRequestDTO` e `PratoResponseDTO` (implementados como Java Records).
- **Validações:** Regras aplicadas nos DTOs utilizando `@Valid`, `@NotBlank`, `@NotNull`, `@Positive`.
- **Regra de Negócio Customizada:** O sistema bloqueia a criação/atualização de um prato se já existir outro com o mesmo nome cadastrado no banco de dados.
- **Tratamento Global de Erros:** Configurado com `@ControllerAdvice` para capturar `MethodArgumentNotValidException` (Erro 400) e `PratoNaoEncontradoException` (Erro 404), retornando JSON customizado e amigável.

### 🌟 Desafios Bônus Concluídos
1. Endpoint **PATCH** (`/pratos/{id}/valor`) para atualizar exclusivamente o preço de um prato.
2. Endpoint **GET** (`/pratos/calorias?max=X`) para filtrar pratos com base em um limite de calorias estipulado.
3. Global Exception Handler implementado de ponta a ponta.

## 🛠️ Como Executar o Projeto

1. Certifique-se de ter o **PostgreSQL** instalado e rodando localmente na porta padrão (5432).
2. O banco de dados chamado `delivery_db` será utilizado.
3. Atualize o arquivo `src/main/resources/application.properties` com seu usuário e senha do Postgres (por padrão está com login 'postgres' e senha '123456').
4. Execute o projeto pela raiz com o comando:
   ```bash
   ./mvnw spring-boot:run
   ```
5. A API estará disponível em: `http://localhost:8080/pratos`

## 📌 Principais Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/pratos` | Cria um novo prato |
| `GET` | `/pratos` | Lista todos os pratos |
| `GET` | `/pratos?categoria={cat}` | Lista filtrando pela categoria |
| `GET` | `/pratos/{id}` | Busca um prato específico |
| `PUT` | `/pratos/{id}` | Atualiza todas as informações do prato |
| `PATCH` | `/pratos/{id}/valor` | Atualiza somente o valor (bônus) |
| `DELETE`| `/pratos/{id}` | Remove um prato |
| `GET` | `/pratos/calorias?max={x}`| Filtra pratos até o limite de calorias (bônus) |

---
*Projeto desenvolvido como parte da atividade de programação Web/Back-end.*
