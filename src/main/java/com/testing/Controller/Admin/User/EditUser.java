package com.testing.Controller.Admin.User;

import com.testing.Controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.service.UserService;
import com.testing.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.testing.Exception.DBException;

public class EditUser implements Command {
    private final static Logger logger = LoggerFactory.getLogger(EditUser.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        UserService userService = new UserServiceImpl();

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        userService.update(req, resp);

        return req.getContextPath()+"/jsp/admUserMenager.jsp";
    }
}
