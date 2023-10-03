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
  `qualificacao` VARCHAR(30) NOT NULL,
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
  `nome` VARCHAR(250) NOT NULL,
  `descricao` VARCHAR(6000) NOT NULL,
  `id_categorias` INT NOT NULL,
  `id_cidades` INT NOT NULL,
  PRIMARY KEY (`id_produtos`),
  INDEX `FK_CATEGORIAS_PRODUTO_idx` (`id_categorias` ASC) VISIBLE,
  INDEX `FK_CIDADES_PRODUTOS_idx` (`id_cidades` ASC) VISIBLE,
  CONSTRAINT `FK_CATEGORIAS_PRODUTO`
    FOREIGN KEY (`id_categorias`)
    REFERENCES `db_projeto_integrador`.`categorias` (`id_categorias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CIDADES_PRODUTOS`
    FOREIGN KEY (`id_cidades`)
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
-- Table `db_projeto_integrador`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_projeto_integrador`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(250) NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `password` VARCHAR(25) NOT NULL,
  `user_roles` VARCHAR(50) NOT NULL,
  `username` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`))
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
