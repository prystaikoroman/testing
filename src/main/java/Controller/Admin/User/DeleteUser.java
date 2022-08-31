package Controller.Admin.User;

import Controller.Command;
import DAO.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Exception.DBException;

public class DeleteUser implements Command {
    private final static Logger logger = LoggerFactory.getLogger(DeleteUser.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        UserService userService = new UserServiceImpl();

        userService.delete(req, resp);
        return req.getContextPath()+"/jsp/admUserMenager.jsp";
    }
}
