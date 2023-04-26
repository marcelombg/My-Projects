CREATE DATABASE IF NOT EXISTS db_projeto_integrador;
USE db_projeto_integrador;


CREATE TABLE IF NOT EXISTS endereco (
	id_endereco INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    cep VARCHAR(30),
    logradouro VARCHAR(100) NOT NULL,
    numero INT,
    complemento VARCHAR(50),
    localidade VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    pais VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
	id_usuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(30) NOT NULL,
    sobrenome VARCHAR(80) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    id_endereco INT NOT NULL
);

CREATE TABLE IF NOT EXISTS estabelecimento (
	id_estabelecimento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    cnpj VARCHAR(30) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    id_endereco INT NOT NULL
);

CREATE TABLE IF NOT EXISTS destino (
	id_destino INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_estabelecimetno INT NOT NULL,
    tipo_destino VARCHAR(50) NOT NULL,
    qualificacao VARCHAR(50) NOT NULL,
    descricao TEXT NOT NULL,
    url_imagem VARCHAR(200) NOT NULL
    
);

CREATE TABLE IF NOT EXISTS reserva (
	id_reserva INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_usuario INT NOT NULL,
    id_destino INT NOT NULL,
    data_reserva DATETIME NOT NULL,
    descricao_reserva VARCHAR(250) NOT NULL
);