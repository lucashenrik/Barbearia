CREATE TABLE tb_barbeiro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    telefone VARCHAR(11) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role TEXT NOT NULL,
    administrador_id BIGINT,
    FOREIGN KEY (administrador_id) REFERENCES tb_administrador(id)
);

CREATE TABLE tb_cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    telefone VARCHAR(11) NOT NULL
);

CREATE TABLE tb_servico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    barbeiro_id BIGINT,
    FOREIGN KEY (barbeiro_id) REFERENCES tb_barbeiro(id)
);

CREATE TABLE tb_atendimentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_atendimento DATETIME NOT NULL,
    cliente_id BIGINT,
    barbeiro_id BIGINT,
    FOREIGN KEY (barbeiro_id) REFERENCES tb_barbeiro(id),
    FOREIGN KEY (cliente_id) REFERENCES tb_cliente(id)
);

CREATE TABLE tb_horarios_trabalho (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dia_semana INT NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,
    barbeiro_id BIGINT,
    FOREIGN KEY (barbeiro_id) REFERENCES tb_barbeiro(id)
);

CREATE TABLE tb_administrador (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    senha VARCHAR(255) NOT NULL
    role TEXT NOT NULL,
);
