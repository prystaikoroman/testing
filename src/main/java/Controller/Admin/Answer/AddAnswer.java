package Controller.Admin.Answer;

import Controller.Command;
import DAO.AnswerDaoImpl;
import DAO.QuerieDaoImpl;
import model.Answer;
import model.Querie;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswer implements Command {
    private final static Logger logger = Logger.getLogger(AddAnswer.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        AnswerDaoImpl answerDao = new AnswerDaoImpl();
        Answer answer = new Answer();
        answer.setAnswer(req.getParameter("answer"));
        answer.setCorrect(req.getParameter("correct") != null && req.getParameter("correct").equals("on"));
        answer.setQuerie_id(Integer.parseInt(req.getParameter("querie_Id")));
        answerDao.save(answer);
        return req.getContextPath()+"/jsp/admAnswerMenager.jsp";

    }
}
