-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema testing
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema testing
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `testing` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema library_task
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library_task
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library_task` DEFAULT CHARACTER SET utf8 ;
USE `testing` ;

-- -----------------------------------------------------
-- Table `testing`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(150) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `admin` TINYINT(1) NOT NULL DEFAULT 0,
  `locked` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `d_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing`.`subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing`.`test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing`.`test` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `task` VARCHAR(250) NOT NULL,
  `subject_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `difficulty` TINYINT(2) NOT NULL,
  `passingTimeMin` SMALLINT(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_test_subject_idx` (`subject_id` ASC) VISIBLE,
  CONSTRAINT `fk_test_subject`
    FOREIGN KEY (`subject_id`)
    REFERENCES `testing`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing`.`querie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing`.`querie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(150) NOT NULL,
  `test_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_test_option_test1_idx` (`test_id` ASC) VISIBLE,
  CONSTRAINT `fk_test_option_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `testing`.`test` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing`.`user_test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing`.`user_test` (
  `user_id` INT NOT NULL,
  `test_id` INT NOT NULL,
  `started` DATETIME NOT NULL DEFAULT NOW(),
  `finished` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`, `test_id`),
  INDEX `fk_user_has_test_test1_idx` (`test_id` ASC) VISIBLE,
  INDEX `fk_user_has_test_user1_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `test_id_UNIQUE` (`test_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_test_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `testing`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_test_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `testing`.`test` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `answer` VARCHAR(150) NOT NULL,
  `correct` TINYINT(1) NOT NULL DEFAULT 0,
  `querie_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_answer_querie1_idx` (`querie_id` ASC) VISIBLE,
  CONSTRAINT `fk_answer_querie1`
    FOREIGN KEY (`querie_id`)
    REFERENCES `testing`.`querie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing`.`user_test_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing`.`user_test_answer` (
  `user_test_user_id` INT NOT NULL,
  `user_test_test_id` INT NOT NULL,
  `answer_id` INT NOT NULL,
  `correct` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_test_user_id`, `user_test_test_id`, `answer_id`),
  INDEX `fk_user_test_has_answer_answer1_idx` (`answer_id` ASC) VISIBLE,
  INDEX `fk_user_test_has_answer_user_test1_idx` (`user_test_user_id` ASC, `user_test_test_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_test_has_answer_user_test1`
    FOREIGN KEY (`user_test_user_id` , `user_test_test_id`)
    REFERENCES `testing`.`user_test` (`user_id` , `test_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_test_has_answer_answer1`
    FOREIGN KEY (`answer_id`)
    REFERENCES `testing`.`answer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


INSERT INTO `testing`.`user` (`id`, `login`, `email`, `lastname`, `password`, `admin`, `locked`) VALUES ('0', 'admin', 'admin@gmail.com', 'admin', 'admin', '1', '0');


