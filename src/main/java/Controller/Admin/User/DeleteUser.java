package Controller.Admin.User;

import Controller.Command;
import DAO.UserDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser implements Command {
    private final static Logger logger = Logger.getLogger(DeleteUser.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        UserDaoImpl userDao = new UserDaoImpl();

        userDao.delete(Integer.parseInt(req.getParameter("userId")));
        return req.getContextPath()+"/jsp/admUserMenager.jsp";
    }
}
