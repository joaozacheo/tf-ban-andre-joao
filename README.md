# Sistema de Gerenciamento de Loja de Jogos

Sistema desenvolvido em Java para gerenciar uma loja de jogos que oferece diferentes tipos de jogos (Arcades, Tabuleiros, Carteados e RPGs) para uso/locação por clientes.

## Estrutura do Banco de Dados

### Tabelas Principais:

1. **Jogos**: Tabela principal com informações gerais dos jogos
   - id (PK)
   - nome
   - genero
   - idadeMin
   - nJogadores

2. **Arcades**: Especialização de Jogos
   - id (PK, FK -> Jogos)
   - qtdTickets

3. **Tabuleiros**: Especialização de Jogos
   - id (PK, FK -> Jogos)
   - qtdPecas
   - idSala (FK -> Salas, opcional)
   - qtdHoras (opcional)

4. **Carteados**: Especialização de Jogos
   - id (PK, FK -> Jogos)
   - qtdCartas
   - idSala (FK -> Salas, opcional)
   - qtdHoras (opcional)

5. **RPGs**: Especialização de Jogos
   - id (PK, FK -> Jogos)
   - qtdFichasPers
   - qtdPecas
   - idSala (FK -> Salas, opcional)
   - qtdHoras (opcional)

6. **Salas**: Salas disponíveis para jogos
   - id (PK)
   - numero
   - capacidade
   - tipo

7. **Clientes**: Clientes da loja
   - cpf (PK)
   - nome
   - dtNasc
   - frequencia
   - idCartao (opcional)
   - valorCartao (opcional)

8. **Uso**: Relacionamento entre Jogos e Clientes (empréstimos/locações)
   - id (PK)
   - idJogo (FK -> Jogos)
   - cpfCliente (FK -> Clientes)
   - dtHoraEmprestimo
   - dtHoraDevolucao
   - preco


## Funcionalidades Implementadas

### ✅ Operações CRUD completas para todas as tabelas:
- **Inserir** novas tuplas em todas as tabelas
- **Listar** todas as tuplas de todas as tabelas
- **Remover** tuplas em todas as tabelas
- **Atualizar** tuplas em todas as tabelas

### ✅ Consultas com JOIN (2 opções):
1. **Listar Usos com Informações dos Jogos** (opção 33)
   - JOIN entre Uso e Jogos
   - Mostra detalhes do jogo usado em cada empréstimo

2. **Listar Usos com Informações dos Clientes** (opção 34)
   - JOIN entre Uso e Clientes
   - Mostra detalhes do cliente em cada empréstimo

### ✅ Consultas com Subconsulta e Agregação (3 opções):
1. **Receita Total por Jogo** (opção 35)
   - Usa SUM, COUNT e GROUP BY
   - Mostra receita total e quantidade de usos por jogo

2. **Clientes que Mais Gastaram** (opção 36)
   - Usa SUM, COUNT, GROUP BY e HAVING
   - Mostra clientes ordenados por valor gasto

3. **Clientes Frequentes** (opção 37)
   - Usa AVG
   - Mostra clientes com frequencia acima da média

## Como Usar

### 1. Preparar o Banco de Dados

```bash
# Criar o banco de dados PostgreSQL
sudo -u postgres createdb lojajogos

# Executar o script SQL
sudo -u postgres psql -d lojajogos -f schema.sql
```

Ou execute o conteúdo do arquivo `schema.sql` diretamente no PostgreSQL.

### 2. Configurar a Conexão

Verifique as credenciais em `Conexao.java`:
```java
String user = "postgres";
String senha = "postgres";
String url = "jdbc:postgresql://localhost:5432/lojajogos";
```

### 3. Compilar o Projeto

Certifique-se de ter o driver JDBC do PostgreSQL no classpath:

```bash
# No diretório LojaJogos/src
javac -d ../bin Conexao.java Principal.java bean/*.java model/*.java controller/*.java
```

### 4. Executar o Sistema

```bash
# No diretório LojaJogos
java -cp .:postgresql-42.6.0.jar:bin Principal
```

## Tecnologias Utilizadas

- Java
- PostgreSQL
- JDBC (Java Database Connectivity)
- Padrão MVC (Model-View-Controller)

## Autor

Desenvolvido para a disciplina de Banco de Dados I