package Controller.Admin.Answer;

import Controller.Command;
import DAO.AnswerDaoImpl;
import DAO.QuerieDaoImpl;
import model.Answer;
import model.Querie;
import org.apache.log4j.Logger;
import service.AnswerService;
import service.AnswerServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Exception.DBException;

public class EditAnswer implements Command {
    private final static Logger logger = Logger.getLogger(EditAnswer.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        AnswerService answerService = new AnswerServiceImpl();

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        answerService.update(req,resp);

        return req.getContextPath()+"/jsp/admAnswerMenager.jsp";
    }
}
