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
DROP procedure IF EXISTS `testing`.`insert_user_test_answer`;
;

DELIMITER $$
USE `testing`$$
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
;











USE `testing`;
DROP procedure IF EXISTS `testing`.`user_tests_result`;
;

DELIMITER $$
USE `testing`$$
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
;