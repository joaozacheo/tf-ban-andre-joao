-- Script SQL para criar o banco de dados da Loja de Jogos
-- Execute este script no PostgreSQL antes de rodar o programa Java

-- Criar o banco de dados (se não existir)
-- CREATE DATABASE lojajogos;

-- Conectar ao banco de dados
-- \c lojajogos;

-- Tabela Jogos (entidade principal)
CREATE TABLE IF NOT EXISTS Jogos (
    id INTEGER PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    idadeMin INTEGER NOT NULL,
    nJogadores INTEGER NOT NULL
);

-- Tabela Arcades (especialização de Jogos)
CREATE TABLE IF NOT EXISTS Arcades (
    id INTEGER PRIMARY KEY,
    qtdTickets INTEGER NOT NULL,
    FOREIGN KEY (id) REFERENCES Jogos(id) ON DELETE CASCADE
);

-- Tabela Salas
CREATE TABLE IF NOT EXISTS Salas (
    id INTEGER PRIMARY KEY,
    numero INTEGER NOT NULL,
    capacidade INTEGER NOT NULL,
    tipo VARCHAR(50) NOT NULL
);

-- Tabela Tabuleiros (especialização de Jogos)
CREATE TABLE IF NOT EXISTS Tabuleiros (
    id INTEGER PRIMARY KEY,
    qtdPecas INTEGER NOT NULL,
    idSala INTEGER,
    qtdHoras INTEGER,
    FOREIGN KEY (id) REFERENCES Jogos(id) ON DELETE CASCADE,
    FOREIGN KEY (idSala) REFERENCES Salas(id) ON DELETE SET NULL
);

-- Tabela Carteados (especialização de Jogos)
CREATE TABLE IF NOT EXISTS Carteados (
    id INTEGER PRIMARY KEY,
    qtdCartas INTEGER NOT NULL,
    idSala INTEGER,
    qtdHoras INTEGER,
    FOREIGN KEY (id) REFERENCES Jogos(id) ON DELETE CASCADE,
    FOREIGN KEY (idSala) REFERENCES Salas(id) ON DELETE SET NULL
);

-- Tabela RPGs (especialização de Jogos)
CREATE TABLE IF NOT EXISTS RPGs (
    id INTEGER PRIMARY KEY,
    qtdFichasPers INTEGER NOT NULL,
    qtdPecas INTEGER NOT NULL,
    idSala INTEGER,
    qtdHoras INTEGER,
    FOREIGN KEY (id) REFERENCES Jogos(id) ON DELETE CASCADE,
    FOREIGN KEY (idSala) REFERENCES Salas(id) ON DELETE SET NULL
);

-- Tabela Clientes
CREATE TABLE IF NOT EXISTS Clientes (
    cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    dtNasc DATE NOT NULL,
    frequencia INTEGER NOT NULL DEFAULT 0,
    idCartao INTEGER,
    valorCartao INTEGER
);

-- Tabela Uso (relacionamento entre Jogos e Clientes)
CREATE TABLE IF NOT EXISTS Uso (
    id INTEGER PRIMARY KEY,
    idJogo INTEGER NOT NULL,
    cpfCliente VARCHAR(11) NOT NULL,
    dtHoraEmprestimo TIMESTAMP NOT NULL,
    dtHoraDevolucao TIMESTAMP NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (idJogo) REFERENCES Jogos(id) ON DELETE CASCADE,
    FOREIGN KEY (cpfCliente) REFERENCES Clientes(cpf) ON DELETE CASCADE
);

-- Inserir alguns dados de exemplo

-- Jogos
INSERT INTO Jogos (id, nome, genero, idadeMin, nJogadores) VALUES
(1, 'Pac-Man', 'Arcade', 6, 1),
(2, 'Street Fighter II', 'Luta', 10, 2),
(3, 'Monopoly', 'Estratégia', 8, 4),
(4, 'War', 'Estratégia', 10, 6),
(5, 'Uno', 'Cartas', 7, 4),
(6, 'Poker', 'Cartas', 18, 8),
(7, 'D&D 5e', 'RPG', 12, 6),
(8, 'Pathfinder', 'RPG', 14, 5);

-- Arcades
INSERT INTO Arcades (id, qtdTickets) VALUES
(1, 100),
(2, 150);

-- Salas
INSERT INTO Salas (id, numero, capacidade, tipo) VALUES
(1, 101, 6, 'RPG'),
(2, 102, 8, 'Tabuleiro'),
(3, 103, 10, 'Carteado');

-- Tabuleiros
INSERT INTO Tabuleiros (id, qtdPecas, idSala, qtdHoras) VALUES
(3, 200, 2, 3),
(4, 150, 2, 4);

-- Carteados
INSERT INTO Carteados (id, qtdCartas, idSala, qtdHoras) VALUES
(5, 108, 3, 2),
(6, 52, 3, 3);

-- RPGs
INSERT INTO RPGs (id, qtdFichasPers, qtdPecas, idSala, qtdHoras) VALUES
(7, 6, 50, 1, 4),
(8, 5, 40, 1, 5);

-- Clientes
INSERT INTO Clientes (cpf, nome, dtNasc, frequencia, idCartao, valorCartao) VALUES
('12345678901', 'João Silva', '1995-05-15', 5, 1, 100),
('98765432100', 'Maria Santos', '1998-08-22', 10, 2, 200),
('11122233344', 'Pedro Oliveira', '2000-03-10', 3, NULL, NULL);

-- Usos
INSERT INTO Uso (id, idJogo, cpfCliente, dtHoraEmprestimo, dtHoraDevolucao, preco) VALUES
(1, 1, '12345678901', '2024-11-01 14:00:00', '2024-11-01 15:30:00', 15.00),
(2, 7, '98765432100', '2024-11-05 18:00:00', '2024-11-05 22:00:00', 50.00),
(3, 3, '11122233344', '2024-11-10 10:00:00', '2024-11-10 13:00:00', 30.00),
(4, 2, '12345678901', '2024-11-15 16:00:00', '2024-11-15 17:00:00', 20.00);

-- Consultas úteis para testar

-- Listar todos os jogos com suas especializações
SELECT j.*, 
       CASE 
           WHEN a.id IS NOT NULL THEN 'Arcade'
           WHEN t.id IS NOT NULL THEN 'Tabuleiro'
           WHEN c.id IS NOT NULL THEN 'Carteado'
           WHEN r.id IS NOT NULL THEN 'RPG'
           ELSE 'Genérico'
       END as tipo_jogo
FROM Jogos j
LEFT JOIN Arcades a ON j.id = a.id
LEFT JOIN Tabuleiros t ON j.id = t.id
LEFT JOIN Carteados c ON j.id = c.id
LEFT JOIN RPGs r ON j.id = r.id;

-- Total de receita por jogo
SELECT j.nome, SUM(u.preco) as receita_total, COUNT(u.id) as total_usos
FROM Jogos j
LEFT JOIN Uso u ON j.id = u.idJogo
GROUP BY j.id, j.nome
ORDER BY receita_total DESC;

-- Clientes que mais gastaram
SELECT c.nome, c.cpf, SUM(u.preco) as total_gasto, COUNT(u.id) as total_usos
FROM Clientes c
LEFT JOIN Uso u ON c.cpf = u.cpfCliente
GROUP BY c.cpf, c.nome
HAVING SUM(u.preco) > 0
ORDER BY total_gasto DESC;
