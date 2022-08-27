USE `testing`;
DROP procedure IF EXISTS `user_tests_finished_upd`;

USE `testing`;
DROP procedure IF EXISTS `testing`.`user_tests_finished_upd`;
;

DELIMITER $$
USE `testing`$$
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
;










USE `testing`;
DROP procedure IF EXISTS `insert_user_test_answer`;

DELIMITER $$
USE `testing`$$
CREATE PROCEDURE `insert_user_test_answer` (in IN_user_test_user_id  int,
											in IN_user_test_test_id  int,
											in IN_answer_id  int,
											in IN_correct  int)
BEGIN
if exists(Select * from USER_TEST_ANSWER where user_test_user_id= IN_user_test_user_id  and
											       user_test_test_id= IN_user_test_test_id  and
												   answer_id = IN_answer_id  )
then
begin
update USER_TEST_ANSWER
 set correct = IN_correct
 Where  user_test_user_id= IN_user_test_user_id  and
		user_test_test_id= IN_user_test_test_id  and
		answer_id = IN_answer_id ;
 end;
else
   begin
    INSERT INTO USER_TEST_ANSWER
                     (user_test_user_id, user_test_test_id, answer_id, correct)
                    VALUES
                     (IN_user_test_user_id ,
					 IN_user_test_test_id ,
					 IN_answer_id,
					 IN_correct);
    end;
  end if;
END$$

DELIMITER ;




