package com.testing.Controller.Admin.Querie;

import com.testing.Controller.Command;
import com.testing.service.AnswerService;
import com.testing.service.AnswerServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.testing.Exception.DBException;

public class UserQuerieCommit implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws IOException, DBException {
        AnswerService answerService = new AnswerServiceImpl();
        answerService.insertUser_Test_Answer( req,  resp,  servletContext);
        return req.getContextPath()+"/jsp/admQuerieMenager.jsp";
    }
}
