-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema testing_test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema testing_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `testing_test` DEFAULT CHARACTER SET utf8 ;
USE `testing_test` ;

-- -----------------------------------------------------
-- Table `testing_test`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing_test`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `answer` VARCHAR(150) NOT NULL,
  `correct` TINYINT(1) NOT NULL DEFAULT '0',
  `querie_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `testing_test`.`querie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing_test`.`querie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(150) NOT NULL,
  `test_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `testing_test`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing_test`.`subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `testing_test`.`test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing_test`.`test` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `task` VARCHAR(250) NOT NULL,
  `subject_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `difficulty` TINYINT NOT NULL,
  `passingTimeMin` SMALLINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `testing_test`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing_test`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(150) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `admin` TINYINT(1) NOT NULL DEFAULT '0',
  `locked` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `d_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `testing_test`.`user_test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing_test`.`user_test` (
  `user_id` INT NOT NULL,
  `test_id` INT NOT NULL,
  `started` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `finished` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`, `test_id`),
  INDEX `fk_user_has_test_user1_idx` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `testing_test`.`user_test_answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testing_test`.`user_test_answer` (
  `user_test_user_id` INT NOT NULL,
  `user_test_test_id` INT NOT NULL,
  `answer_id` INT NOT NULL,
  `correct` TINYINT(1) NOT NULL DEFAULT '0',
  `selected` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_test_user_id`, `user_test_test_id`, `answer_id`),
  INDEX `fk_user_test_has_answer_user_test1_idx` (`user_test_user_id` ASC, `user_test_test_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

USE `testing_test` ;

-- -----------------------------------------------------
-- procedure insert_user_test_answer
-- -----------------------------------------------------

DELIMITER $$
USE `testing_test`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_user_test_answer`(in IN_user_test_user_id  int,
											in IN_user_test_test_id  int,
											in IN_answer_id  int,
											in IN_correct  int,
                                            in IN_selected  int)
BEGIN
if exists(Select * from USER_TEST_ANSWER where user_test_user_id= IN_user_test_user_id  and
											       user_test_test_id= IN_user_test_test_id  and
												   answer_id = IN_answer_id  )
then
begin
update USER_TEST_ANSWER
 set correct = IN_correct,
 selected = IN_selected
 Where  user_test_user_id= IN_user_test_user_id  and
		user_test_test_id= IN_user_test_test_id  and
		answer_id = IN_answer_id ;
 end;
else
   begin
    INSERT INTO USER_TEST_ANSWER
                     (user_test_user_id, user_test_test_id, answer_id, correct, selected)
                    VALUES
                     (IN_user_test_user_id ,
					 IN_user_test_test_id ,
					 IN_answer_id,
					 IN_correct,
                     IN_selected);
    end;
  end if;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure user_tests_finished_upd
-- -----------------------------------------------------

DELIMITER $$
USE `testing_test`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `user_tests_finished_upd`(in IN_user_Id  int)
BEGIN

drop table tbl_test_for_upd;
CREATE TABLE tbl_test_for_upd
SELECT `user_id`,`test_id`,`started`,`finished`,
case
			when `finished` = 0
            then case
				when date_add(started,interval passingTimeMin minute) >= NOW()
                then 0
                else 1
                end
			else 1 end
            as `finished_Upd`,

`id`,`task`,`subject_id`,`name`,`difficulty`,`passingTimeMin`
from USER_TEST left outer Join TEST on  test_id = id where user_id = IN_user_Id;

UPDATE USER_TEST ut
INNER JOIN tbl_test_for_upd as t1 on ut.`user_id` = t1.`user_id` and ut.`test_id` = t1.`test_id`
SET  ut.`finished` = 1
where  t1.`finished_Upd`!= ut.`finished`  and ut.user_id = IN_user_Id;


END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure user_tests_result
-- -----------------------------------------------------

DELIMITER $$
USE `testing_test`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `user_tests_result`(in IN_user_id int)
BEGIN

drop table tbl_user_tests_result;

CREATE TABLE tbl_user_tests_result
select
user_id,
test_id,
count(querie_id) as count_queries,
sum(querie_correct) as sum_querie_correct
from(
	Select tbl.user_id,
	tbl.test_id,
	 tbl.count_querie_answers,
	 tbl.count_answer_submited_correct,
	 tbl.querie_id,

	case when tbl.count_querie_answers = tbl.count_answer_submited_correct then 1 else 0 end as querie_correct
	 from(
		select
		ut.user_id, ut.test_id, q.id as querie_id,
-- 		(select count(id) from querie where querie.test_id = ut.test_id ) as count_queries,
		(Select count(id) from answer where querie_id = q.id) as count_querie_answers,
		(Select count(user_test_answer.correct)
			 from user_test_answer left outer join answer on answer.id = user_test_answer.answer_id
			 where user_test_answer.user_test_test_id = ut.test_id and user_test_answer.user_test_user_id = ut.user_id and answer.querie_id = q.id) as count_answer_submited_correct
		 , a.id as answer_id, a.correct as db_answer_correct, uta.correct as u_answer_correct
		from user_test ut left outer join
		test t on ut.test_id = t.id left outer join
		querie q on t.id=q.test_id left outer join
		answer a on q.id=a.querie_id left outer join
		user_test_answer uta on ut.user_id=uta.user_test_user_id and
					ut.test_id=uta.user_test_test_id and
					a.id=uta.answer_id
		where
		-- ut.finished = 1 and
		ut.user_id = IN_user_id
		-- and  ut.test_id = 2
	and q.id is not null
	-- and	a.id is not null
		group by ut.user_id, ut.test_id, q.id
	) as tbl
) as tbl2
group by test_id;

END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
