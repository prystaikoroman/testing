package com.testing.service;

import com.testing.model.Answer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.testing.Exception.DBException;
public interface AnswerService {
    Answer findById(int id);

    boolean save(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean update(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean delete(int id) throws DBException;
    boolean insertUser_Test_Answer(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException;
    List<Answer> getAllAnswers(int id, int currentPage, int numOfRecords) throws DBException;
    List<Answer> getAllAnswers_UserSubmition(int id, int currentPage, int numOfRecords) throws DBException;
    Integer getNumberOfRows() throws DBException;

}
