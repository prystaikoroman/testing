package service;

import DAO.AnswerDao;
import DAO.AnswerDaoImpl;
import DAO.QuerieDao;
import DAO.QuerieDaoImpl;
import model.Answer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AnswerServiceImpl implements AnswerService{
    public AnswerServiceImpl() {
    }

    private final static Logger logger = Logger.getLogger(QuerieServiceImpl.class);

    private final AnswerDao answerDao = new AnswerDaoImpl();
    @Override
    public Answer findById(int id) {
        return answerDao.findById(id);
    }

    @Override
    public boolean save(HttpServletRequest req, HttpServletResponse resp) {
        Answer answer = new Answer();
        answer.setAnswer(req.getParameter("answer"));
        answer.setCorrect(req.getParameter("correct") != null && req.getParameter("correct").equals("on"));
        answer.setQuerie_id(Integer.parseInt(req.getParameter("querie_Id")));

        return answerDao.save(answer);
    }

    @Override
    public boolean update(HttpServletRequest req, HttpServletResponse resp) {
        Answer answer = new Answer();

        answer.setId(Integer.parseInt(req.getParameter("answerId")));
        answer.setAnswer(req.getParameter("answer"));
        answer.setCorrect(req.getParameter("correct") != null && req.getParameter("correct").equals("on"));
        answer.setQuerie_id(Integer.parseInt(req.getParameter("querie_Id")));

        return answerDao.update(answer);
    }

    @Override
    public boolean delete(int id) {
        return answerDao.delete(id);
    }

    @Override
    public boolean insertUser_Test_Answer(HttpServletRequest req, HttpServletResponse resp) {
        int user_Id = 0;
        int test_Id = 0;
        int answer_Id = 0;
        boolean correct = false;
        return answerDao.insertUser_Test_Answer(user_Id,  test_Id,  answer_Id,  correct);
    }

    @Override
    public List<Answer> getAllAnswers(int id, int currentPage, int numOfRecords) {
        return answerDao.getAllAnswers(id, currentPage, numOfRecords);
    }

    @Override
    public Integer getNumberOfRows() {
        return answerDao.getNumberOfRows();
    }
}
