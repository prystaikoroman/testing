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

@WebServlet("/deleteUser")
public class DeleteUserServlete extends HttpServlet {
    private final static Logger logger = Logger.getLogger(DeleteUserServlete.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doPost");
        UserDaoImpl userDao = new UserDaoImpl();

        userDao.delete(Integer.parseInt(req.getParameter("userId")));
        HttpSession session = req.getSession();//create session
//        session.setAttribute("Admin", session.getAttribute("login"));
        getServletContext().setAttribute("login", session.getAttribute("Admin"));
        getServletContext().setAttribute("users", userDao.getAllUser());
        getServletContext().setAttribute("Admin", session.getAttribute("Admin"));
        resp.sendRedirect(req.getContextPath()+"/jsp/admin.jsp");

    }
}
