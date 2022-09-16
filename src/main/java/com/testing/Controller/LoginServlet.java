package com.testing.Controller;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.testing.Exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doGet command ==>" + req.getParameter("command"));
        process(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        logger.info("entered#doPost command ==>" + req.getParameter("command"));
        process(req, resp);
    }

    protected void process(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
            logger.info("entered#process");
            ServletContext servletContext = getServletContext();
            HttpSession session = req.getSession();//create session
            String commandName = req.getParameter("command");
            Command command = LoginCommandContainer.getCommand(commandName);
            String address = req.getContextPath() + "/jsp/login.jsp";

            if (command != null) {
                try {
                    address = command.execute(req, resp, servletContext);
                } catch (DBException e) {
                    session.setAttribute("errMessage", e.getMessage());
                    address = req.getContextPath() + "/jsp/authError.jsp";
                }
            }
            resp.sendRedirect(address);



    }
}
