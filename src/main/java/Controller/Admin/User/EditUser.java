package Controller.Admin.User;

import Controller.Command;
import DAO.UserDaoImpl;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditUser implements Command {
    private final static Logger logger = Logger.getLogger(EditUser.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) {
        logger.info("entered#execute");
        UserService userService = new UserServiceImpl();

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        userService.update(req, resp);

        return req.getContextPath()+"/jsp/admUserMenager.jsp";
    }
}
