package com.testing.DAO;

import com.testing.model.Answer;

import java.sql.Connection;
import java.util.List;
import com.testing.Exception.DBException;
import com.testing.model.User_Test_Answer;

public interface AnswerDao {
    Answer findById(int id);

    boolean save(Answer answer) throws DBException;

    boolean update(Answer answer) throws DBException;

    boolean delete(int id) throws DBException;

    boolean insertUser_Test_Answer(Connection connection, User_Test_Answer user_test_answer) throws DBException;
    List<Answer> getAllAnswers(int id, int currentPage, int numOfRecords) throws DBException;
    List<Answer> getAllAnswers_UserSubmition(int id, int currentPage, int numOfRecords) throws DBException;
    Integer getNumberOfRows() throws DBException;
}
