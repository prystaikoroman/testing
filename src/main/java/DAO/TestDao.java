package DAO;

import model.Test;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TestDao {
    Test findById(int id);

    Test findByTask(String email);

    Test findByName(String login);

    boolean save(Test test);

    boolean update(Test test);

    boolean delete(int id);
    boolean insertUser_Test(int user_Id, int test_Id);
    boolean User_Tests_Finished_Upd(int id);
    List<Test> getAllUserTests(int id, int subject_id, int currentPage, int numOfRecords);
    List<Test> getAllTests(int id, int currentPage, int numOfRecords);
    Integer getNumberOfRows();
}
