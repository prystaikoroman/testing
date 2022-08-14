package Controller;

import DAO.UserDaoImpl;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(EditUserServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doPost");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();

        user.setId(Integer.parseInt(req.getParameter("userId")));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setLastname(req.getParameter("lastname"));
        user.setAdmin(req.getParameter("admin") != null && req.getParameter("admin").equals("on"));
        user.setLocked(req.getParameter("locked") != null && req.getParameter("locked").equals("on"));

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        userDao.update(user);
        HttpSession session = req.getSession();//create session
//        session.setAttribute("Admin", session.getAttribute("login"));
        getServletContext().setAttribute("login", session.getAttribute("Admin"));
        getServletContext().setAttribute("users", userDao.getAllUser());
        getServletContext().setAttribute("Admin", session.getAttribute("Admin"));
        resp.sendRedirect(req.getContextPath()+"/jsp/admin.jsp");

    }
}
