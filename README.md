# 🎢 ParqueDiversoes

Sistema desenvolvido em Java utilizando JavaFX e PostgreSQL para gerenciamento de bilheterias e atrações de um parque de diversões.

---

## 📋 Funcionalidades Implementadas

- Cadastro de novos clientes
- Login de clientes existentes
- Visualização das bilheterias disponíveis
- Compra de ingressos (com seleção da forma de pagamento)
- Visualização dos ingressos comprados
- Visualização das atrações disponíveis
- Participação em atrações (com controle de ingresso válido)
- Tratamento de exceções (como falta de ingressos e reutilização proibida)
- Interface gráfica moderna usando JavaFX com CSS personalizado

---

## 🛠️ Tecnologias utilizadas

- Java 17
- JavaFX 20
- PostgreSQL 15
- SceneBuilder (para construção das interfaces FXML)

---

## 🗄️ Banco de Dados

O projeto utiliza um banco PostgreSQL com as seguintes tabelas:

- `cliente`
- `bilheteria`
- `ingresso`
- `atracao`
- `atracao_cliente`

Caso necessário, o script SQL para criação/modificação de tabelas está incluso.

---

## 📥 Como executar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/Adison13/ParqueDiversoes.git
