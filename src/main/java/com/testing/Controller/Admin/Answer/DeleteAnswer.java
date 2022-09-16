package com.testing.Controller.Admin.Answer;

import com.testing.Controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.service.AnswerService;
import com.testing.service.AnswerServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.testing.Exception.DBException;

public class DeleteAnswer implements Command {
    private final static Logger logger = LoggerFactory.getLogger(DeleteAnswer.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        AnswerService answerService = new AnswerServiceImpl();

        answerService.delete(Integer.parseInt(req.getParameter("answerId")));
        return req.getContextPath()+"/jsp/admAnswerMenager.jsp";
    }
}
