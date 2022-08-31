package DAO;

import model.Test;
import model.User_Test;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;
import Exception.DBException;
public interface TestDao {
    boolean User_Tests_Result(Connection con, int user_id) throws DBException;

    Test findById(int id);
    User_Test findUser_TestById(int user_id, int test_id) throws DBException;

    Test findByTask(String email);

    Test findByName(String login) throws DBException;

    boolean save(Test test) throws DBException;

    boolean update(Test test) throws DBException;

    boolean delete(int id) throws DBException;
    boolean insertUser_Test(int user_Id, int test_Id) throws DBException;
    boolean User_Tests_Finished_Upd(int id) throws DBException;
    List<Test> getAllUserTests(Connection con, int id, int subject_id, int currentPage, int numOfRecords) throws DBException;
    List<Test> getAllTests(int id, int currentPage, int numOfRecords);
    Integer getNumberOfRows() throws DBException;
}
