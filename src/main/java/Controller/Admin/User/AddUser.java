package Controller.Admin.User;

import Controller.Command;
import DAO.UserDaoImpl;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUser implements Command {
    private final static Logger logger = Logger.getLogger(AddUser.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        UserService userService = new UserServiceImpl();
        userService.save(req, resp);
        return req.getContextPath()+"/jsp/admUserMenager.jsp";

    }
}
