# 🏡 Real Estate Manager

Um sistema web em **Spring Boot + Thymeleaf** para gestão de imóveis.  
Permite que utilizadores adicionem, listem e filtrem propriedades (compra, venda ou arrendamento), com suporte para upload e visualização de imagens.

---

## ⚙️ Funcionalidades

- Registo e login de utilizadores (com Spring Security).
- Adicionar imóveis com:
  - Título
  - Descrição
  - Preço (€)
  - Localização
  - Tipo de imóvel
  - Quartos
  - Casas de banho
  - Área (m²)
  - 📷 Upload de múltiplas imagens
- Listagem de imóveis na página inicial.
- Filtro por localização, número de quartos e preço.
- Servidor de imagens (configurado via `WebConfig`).

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Security**
- **Spring JDBC (JdbcTemplate)**
- **Thymeleaf**
- **PostgreSQL ou MySQL**
- **HTML + CSS**
