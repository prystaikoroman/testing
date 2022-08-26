package service;

import model.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AnswerService {
    Answer findById(int id);

    boolean save(HttpServletRequest req, HttpServletResponse resp);

    boolean update(HttpServletRequest req, HttpServletResponse resp);

    boolean delete(int id);
    boolean insertUser_Test_Answer(HttpServletRequest req, HttpServletResponse resp);
    List<Answer> getAllAnswers(int id, int currentPage, int numOfRecords);
    Integer getNumberOfRows();

}
