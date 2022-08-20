package DAO;

import model.Answer;
import model.Querie;

import java.util.List;

public interface AnswerDao {
    Answer findById(int id);

    boolean save(Answer answer);

    boolean update(Answer answer);

    boolean delete(int id);

    List<Answer> getAllAnswers(int id, int currentPage, int numOfRecords);
    Integer getNumberOfRows();
}
