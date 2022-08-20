package Controller.Admin.Answer;

import Controller.Command;
import DAO.AnswerDaoImpl;
import DAO.QuerieDaoImpl;
import model.Answer;
import model.Querie;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditAnswer implements Command {
    private final static Logger logger = Logger.getLogger(EditAnswer.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        AnswerDaoImpl answerDao = new AnswerDaoImpl();
        Answer answer = new Answer();

        answer.setId(Integer.parseInt(req.getParameter("answerId")));
        answer.setAnswer(req.getParameter("answer"));
        answer.setCorrect(req.getParameter("correct") != null && req.getParameter("correct").equals("on"));
        answer.setQuerie_id(Integer.parseInt(req.getParameter("querie_Id")));

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        answerDao.update(answer);

        return req.getContextPath()+"/jsp/admAnswerMenager.jsp";
    }
}
