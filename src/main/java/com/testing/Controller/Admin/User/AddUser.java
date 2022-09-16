package com.testing.Controller.Admin.User;

import com.testing.Controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.service.UserService;
import com.testing.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.testing.Exception.DBException;
public class AddUser implements Command {
    private final static Logger logger = LoggerFactory.getLogger(AddUser.class);
    String command;

    public AddUser() {
    }

    public AddUser(String command) {
        this.command = command;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws IOException, DBException {
        logger.info("entered#execute");
        UserService userService = new UserServiceImpl();
        userService.save(req, resp);
        String adress = "";
        if (command.equals("addUser")) {
            adress = req.getContextPath() + "/jsp/admUserMenager.jsp";

        } else {
            adress = req.getContextPath() + "/jsp/login.jsp";

        }
        return adress;

    }
}
