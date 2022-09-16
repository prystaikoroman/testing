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

public class EditAnswer implements Command {
    private final static Logger logger = LoggerFactory.getLogger(EditAnswer.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        AnswerService answerService = new AnswerServiceImpl();

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        answerService.update(req,resp);

        return req.getContextPath()+"/jsp/admAnswerMenager.jsp";
    }
}
