package Controller.Admin.User;

import Controller.Command;
import DAO.UserDaoImpl;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUser implements Command {
    private final static Logger logger = Logger.getLogger(AddUser.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setLastname(req.getParameter("lastname"));
        user.setAdmin(req.getParameter("admin") != null && req.getParameter("admin").equals("on"));
        user.setLocked(req.getParameter("locked") != null && req.getParameter("locked").equals("on"));
        userDao.save(user);
        return req.getContextPath()+"/jsp/admUserMenager.jsp";

    }
}
