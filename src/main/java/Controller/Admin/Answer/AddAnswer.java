package Controller.Admin.Answer;

import Controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AnswerService;
import service.AnswerServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Exception.DBException;

public class AddAnswer implements Command {
    private final static Logger logger = LoggerFactory.getLogger(AddAnswer.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        AnswerService answerService = new AnswerServiceImpl();
        answerService.save(req, resp);
        return req.getContextPath()+"/jsp/admAnswerMenager.jsp";

    }
}
