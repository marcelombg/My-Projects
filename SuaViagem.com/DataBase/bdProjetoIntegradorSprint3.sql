-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_projeto_integrador
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_projeto_integrador
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_projeto_integrador` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_projeto_integrador` ;

-- -----------------------------------------------------
-- Table `db_projeto_integrador`.`caracteristicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`caracteristicas` (
  `id_caracteristicas` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(250) NOT NULL,
  `icone` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id_caracteristicas`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_projeto_integrador`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`categorias` (
  `id_categorias` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(6000) NOT NULL,
  `url_imagem` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id_categorias`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_projeto_integrador`.`cidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`cidades` (
  `id_cidades` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `estado` CHAR(2) NOT NULL,
  `pais` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_cidades`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_projeto_integrador`.`produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`produtos` (
  `id_produtos` INT NOT NULL AUTO_INCREMENT,
  `comentarios` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(6000) NOT NULL,
  `distancia` VARCHAR(255) NOT NULL,
  `facilidades` VARCHAR(255) NOT NULL,
  `link_mapa` VARCHAR(1000) NOT NULL,
  `local_mapa` VARCHAR(1000) NOT NULL,
  `nome` VARCHAR(250) NOT NULL,
  `pontuacao` INT NOT NULL,
  `ver_mais` VARCHAR(1000) NOT NULL,
  `category_entity_id` INT NOT NULL,
  `cidades_entity_id` INT NOT NULL,
  PRIMARY KEY (`id_produtos`),
  INDEX `FK_CATEGORIAS_PRODUTO_idx` (`category_entity_id` ASC) VISIBLE,
  INDEX `FK_CIDADES_PRODUTOS_idx` (`cidades_entity_id` ASC) VISIBLE,
  CONSTRAINT `FK_CATEGORIAS_PRODUTO`
    FOREIGN KEY (`category_entity_id`)
    REFERENCES `db_projeto_integrador`.`categorias` (`id_categorias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CIDADES_PRODUTOS`
    FOREIGN KEY (`cidades_entity_id`)
    REFERENCES `db_projeto_integrador`.`cidades` (`id_cidades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_projeto_integrador`.`imagens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`imagens` (
  `id_imagens` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(250) NOT NULL,
  `url` VARCHAR(250) NOT NULL,
  `id_produtos` INT NOT NULL,
  PRIMARY KEY (`id_imagens`),
  INDEX `FK_PRODUTOS_IMAGENS_idx` (`id_produtos` ASC) VISIBLE,
  CONSTRAINT `FK_PRODUTOS_IMAGENS`
    FOREIGN KEY (`id_produtos`)
    REFERENCES `db_projeto_integrador`.`produtos` (`id_produtos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_projeto_integrador`.`funcoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`funcoes` (
  `id_funcao` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_funcao`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_projeto_integrador`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(250) NOT NULL,
  `sobrenome` VARCHAR(250) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `senha` VARCHAR(25) NOT NULL,
  `id_funcao` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `fk_users_funcoes1_idx` (`id_funcao` ASC) VISIBLE,
  CONSTRAINT `fk_users_funcoes1`
    FOREIGN KEY (`id_funcao`)
    REFERENCES `db_projeto_integrador`.`funcoes` (`id_funcao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_projeto_integrador`.`produtos_caracteristicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`produtos_caracteristicas` (
  `id_produtos_caracteristicas` INT NOT NULL AUTO_INCREMENT,
  `id_produtos` INT NOT NULL,
  `id_caracteristicas` INT NOT NULL,
  PRIMARY KEY (`id_produtos_caracteristicas`),
  INDEX `FK_PRODUTOS_CARACTERISTICAS_idx` (`id_produtos` ASC) VISIBLE,
  INDEX `FK_CARACTERISTICAS_PRODUTOS_idx` (`id_caracteristicas` ASC) VISIBLE,
  CONSTRAINT `FK_PRODUTOS_CARACTERISTICAS`
    FOREIGN KEY (`id_produtos`)
    REFERENCES `db_projeto_integrador`.`produtos` (`id_produtos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CARACTERISTICAS_PRODUTOS`
    FOREIGN KEY (`id_caracteristicas`)
    REFERENCES `db_projeto_integrador`.`caracteristicas` (`id_caracteristicas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_projeto_integrador`.`reservas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`reservas` (
  `id_reserva` INT NOT NULL AUTO_INCREMENT,
  `horario_inicio_reserva` TIME NOT NULL,
  `data_inicial` DATE NOT NULL,
  `data_final` DATE NOT NULL,
  `id_produtos` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_reserva`),
  INDEX `FK_RESERVAS_USERS_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `FK_RESERVAS_PRODUTOS_idx` (`id_produtos` ASC) VISIBLE,
  CONSTRAINT `FK_RESERVAS_USERS`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `db_projeto_integrador`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_RESERVAS_PRODUTOS`
    FOREIGN KEY (`id_produtos`)
    REFERENCES `db_projeto_integrador`.`produtos` (`id_produtos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
