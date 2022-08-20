package Controller.Admin.Answer;

import Controller.Command;
import DAO.QuerieDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswer implements Command {
    private final static Logger logger = Logger.getLogger(DeleteAnswer.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        QuerieDaoImpl querieDao = new QuerieDaoImpl();

        querieDao.delete(Integer.parseInt(req.getParameter("answerId")));
        return req.getContextPath()+"/jsp/admAnswerMenager.jsp";
    }
}
