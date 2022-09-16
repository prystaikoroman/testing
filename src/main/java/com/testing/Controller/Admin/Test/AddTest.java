package com.testing.Controller.Admin.Test;

import com.testing.Controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.service.TestService;
import com.testing.service.TestServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.testing.Exception.DBException;

public class AddTest implements Command {
    private final static Logger logger = LoggerFactory.getLogger(AddTest.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        TestService testService = new TestServiceImpl();
        testService.save(req, resp);
        return req.getContextPath()+"/jsp/admTestMenager.jsp";

    }
}
