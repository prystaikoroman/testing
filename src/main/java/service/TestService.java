package service;

import model.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TestService {
    Test findById(HttpServletRequest req, HttpServletResponse resp);

    Test findByTask(String email);

    Test findByName(String login);

    boolean save(HttpServletRequest req, HttpServletResponse resp);

    boolean update(HttpServletRequest req, HttpServletResponse resp);

    boolean delete(HttpServletRequest req, HttpServletResponse resp);

    boolean User_Tests_Finished_Upd(int id);
    List<Test> getAllUserTests(int user_Id, int subject_id, int currentPage, int numOfRecords);
    List<Test> getAllTests(HttpServletRequest req, HttpServletResponse resp, int currentPage, int numOfRecords);
    Integer getNumberOfRows();

}
