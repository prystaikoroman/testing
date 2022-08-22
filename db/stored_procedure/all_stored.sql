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
from USER_TEST left outer Join TEST on  test_id = id where user_id = @IN_user_Id;

UPDATE USER_TEST ut
INNER JOIN tbl_test_for_upd as t1 on ut.`user_id` = t1.`user_id` and ut.`test_id` = t1.`test_id`
SET  ut.`finished` = 1
where  t1.`finished_Upd`!= ut.`finished`;


END$$

DELIMITER ;
;

