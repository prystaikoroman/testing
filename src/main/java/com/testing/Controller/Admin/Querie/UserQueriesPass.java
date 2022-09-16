package com.testing.Controller.Admin.Querie;

import com.testing.Controller.Command;
import com.testing.service.TestService;
import com.testing.service.TestServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.testing.Exception.DBException;

public class UserQueriesPass implements Command {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws IOException, DBException {
        TestService testService = new TestServiceImpl();
        testService.insertUser_Test( req,  resp,  servletContext);
        return req.getContextPath()+"/jsp/admQuerieMenager.jsp";
    }
}
