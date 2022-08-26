package Controller;

import DAO.LoginDao;
import DAO.UserDaoImpl;
import DTO.LoginDto;
import org.apache.log4j.Logger;
import Exception.AuthException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login implements Command {
    private final static Logger logger = Logger.getLogger(Login.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();//create session
        try {
            String userValidate = null;
            userValidate = userRole(login, password);
            UserDaoImpl userDao = new UserDaoImpl();
            if (userValidate.equals("ADMIN_ROLE")) {
                logger.info("Admin's Home");
                session.setAttribute("Admin", login);
                session.setAttribute("AdminUser", userDao.findByLogin(login));
                req.setAttribute("login", login);
//req.setAttribute("users", userDao.getAllUser());
                return req.getContextPath() + "/jsp/adminIndex.jsp";

            } else if (userValidate.equals("USER_ROLE")) {
                logger.info("User's Home");
                session.setMaxInactiveInterval(10 * 60);
                session.setAttribute("User", login);
                session.setAttribute("UserUser", userDao.findByLogin(login));
                req.setAttribute("userName", login);
                return req.getContextPath() + "/jsp/userIndex.jsp";
            }
        } catch (AuthException e) {
            return req.getContextPath() + "/jsp/registration.jsp";
        } catch (Exception e) {
            logger.error(e.getMessage());
            session.setAttribute("errMessage", e.getMessage());
            return req.getContextPath() + "/jsp/authError.jsp";
        }


        session.setAttribute("errMessage", "something went wrong. try again later.");
        return req.getContextPath() + "/jsp/authError.jsp";
    }

    public static String userRole(String login, String password) throws AuthException {

        LoginDto loginDto = new LoginDto();
        loginDto.setLogin(login);
        loginDto.setPassword(password);

        LoginDao loginDao = new LoginDao();

        return loginDao.authenticateUser(loginDto);
    }
}
