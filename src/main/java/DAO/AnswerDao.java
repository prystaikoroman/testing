package DAO;

import model.Answer;
import model.Querie;

import java.util.List;
import Exception.DBException;
public interface AnswerDao {
    Answer findById(int id);

    boolean save(Answer answer) throws DBException;

    boolean update(Answer answer) throws DBException;

    boolean delete(int id) throws DBException;

    boolean insertUser_Test_Answer(int user_Id, int test_Id, int answer_Id, boolean correct);
    List<Answer> getAllAnswers(int id, int currentPage, int numOfRecords) throws DBException;
    Integer getNumberOfRows() throws DBException;
}
