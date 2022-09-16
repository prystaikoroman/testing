package com.testing.Controller;

import com.testing.DAO.LoginDao;
import com.testing.DAO.UserDaoImpl;
import com.testing.DTO.LoginDto;

import com.testing.Exception.AuthException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import com.testing.Exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.util.DSInstance;

public class Login implements Command {
    private final static Logger logger = LoggerFactory.getLogger(Login.class);
    static DataSource ds = DSInstance.getInstance().getDs();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();//create session
        try {
            String userValidate = null;
            userValidate = userRole(login, password);
            UserDaoImpl userDao = new UserDaoImpl(ds);
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
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            session.setAttribute("errMessage", e.getMessage());
//            return req.getContextPath() + "/jsp/authError.jsp";
        } catch (DBException e) {
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

        LoginDao loginDao = new LoginDao(ds);

         return loginDao.authenticateUser(loginDto);
    }
}
