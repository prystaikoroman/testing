package Controller.ServerSideValidation;

import Controller.LoginServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import Exception.DBException;

@WebServlet("/AjaxLoginValidation")
public class AjaxLoginValidation extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//create session
        UserService userService = new UserServiceImpl();
        String address;
        try {
            userService.findByLogin(req.getParameter("login"));
        } catch (DBException e) {
            logger.error(e.getMessage());
            session.setAttribute("errMessage", e.getMessage());
            address = req.getContextPath() + "/jsp/authError.jsp";
            resp.sendRedirect(address);
        }
    }
}
