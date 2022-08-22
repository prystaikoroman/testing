package Controller;

import DAO.LoginDao;
import DAO.UserDaoImpl;
import DTO.LoginDto;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import Exception.DBException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        LoginDto loginDto = new LoginDto();
        loginDto.setLogin(login);
        loginDto.setPassword(password);

        LoginDao loginDao = new LoginDao();

        String userValidate = null;
        try {
            userValidate = loginDao.authenticateUser(loginDto);
        } catch (DBException e) {
            logger.error(e.getMessage());
        }
        UserDaoImpl userDao = new UserDaoImpl();
        if (userValidate.equals( "ADMIN_ROLE")) {
                logger.info("Admin's Home");
                HttpSession session = req.getSession();//create session

                session.setAttribute("Admin", login);
                session.setAttribute("AdminUser", userDao.findByLogin(login));
                req.setAttribute("login", login);
//req.setAttribute("users", userDao.getAllUser());
                req.getRequestDispatcher("/jsp/adminIndex.jsp").forward(req, resp);

            } else if (userValidate.equals( "USER_ROLE")) {
                logger.info("User's Home");
                HttpSession session = req.getSession();//create session
                session.setMaxInactiveInterval(10 * 60);
                session.setAttribute("User", login);
                session.setAttribute("UserUser", userDao.findByLogin(login));
                req.setAttribute("userName", login);
                req.getRequestDispatcher("/jsp/userIndex.jsp").forward(req,resp);
            }
    }
}
