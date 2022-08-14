package Controller;

import DAO.UserDaoImpl;
import model.User;
import org.apache.log4j.Logger;
import util.DSInstance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(AddUserServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doPost");
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setLastname(req.getParameter("lastname"));
        user.setAdmin(req.getParameter("admin") != null && req.getParameter("admin").equals("on"));
        user.setLocked(req.getParameter("locked") != null && req.getParameter("locked").equals("on"));
        userDao.save(user);
        HttpSession session = req.getSession();//create session
//        session.setAttribute("Admin", session.getAttribute("login"));
        getServletContext().setAttribute("login", session.getAttribute("Admin"));
        getServletContext().setAttribute("users", userDao.getAllUser());
        getServletContext().setAttribute("Admin", session.getAttribute("Admin"));
        resp.sendRedirect(req.getContextPath()+"/jsp/admin.jsp");
    }
}
