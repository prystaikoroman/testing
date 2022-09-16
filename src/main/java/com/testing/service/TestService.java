package com.testing.service;

import com.testing.model.Test;
import com.testing.model.User_Test;
import com.testing.Exception.DBException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TestService {
    Test findById(HttpServletRequest req, HttpServletResponse resp);
    User_Test findUser_TestById(HttpServletRequest req, HttpServletResponse resp);

    Test findByTask(String email);

    Test findByName(String login) throws DBException;

    boolean save(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean update(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean delete(HttpServletRequest req, HttpServletResponse resp) throws DBException;
    boolean insertUser_Test( HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException;

    boolean User_Tests_Finished_Upd(int id) throws DBException;
    List<Test> getAllUserTests(int user_Id, int subject_id, int currentPage, int numOfRecords) throws DBException;
    List<Test> getAllTests(HttpServletRequest req, HttpServletResponse resp, int currentPage, int numOfRecords);
    Integer getNumberOfRows() throws DBException;

}
