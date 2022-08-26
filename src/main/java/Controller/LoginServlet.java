package Controller;

import Controller.Admin.Answer.AdminAnswerCommandContainer;
import DAO.LoginDao;
import DAO.UserDaoImpl;
import DTO.LoginDto;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import Exception.DBException;
import Exception.AuthException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        logger.info("entered#doPost command ==>" + req.getParameter("command"));
        process(req, resp);
    }

        private void process(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
            logger.info("entered#process");
            ServletContext servletContext = getServletContext();

            String commandName = req.getParameter("command");
            Command command = LoginCommandContainer.getCommand(commandName);
            String address = req.getContextPath() + "/jsp/login.jsp";

            if (command != null) {
                try {
                    address = command.execute(req, resp, servletContext);
                } catch (DBException e) {
                    address = req.getContextPath() + "/jsp/authError.jsp";
                    e.printStackTrace();
                }

            }
            resp.sendRedirect(address);



    }
}
