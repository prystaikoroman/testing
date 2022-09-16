package com.testing.Controller.Admin.User;

import com.testing.Controller.Command;
import com.testing.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.service.UserService;
import com.testing.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.testing.Exception.DBException;
//Front Controller Pattern Servlet

@WebServlet("/adminUser")
public class AdminUserServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(AdminUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doPost");
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doGet");
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#process");
        ServletContext servletContext = getServletContext();

        int currentPage = 1;
        int recordsPerPage = 5;
        currentPage = Integer.parseInt(req.getParameter("currentPage"));
        recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));

        String commandName = req.getParameter("command");
        Command command = AdminUserCommandContainer.getCommand(commandName);
        String address = req.getContextPath() + "/jsp/admUserMenager.jsp";
        UserService userService = new UserServiceImpl();
        HttpSession session = req.getSession();//create session
        try {
            if (command != null) {

                address = command.execute(req, resp, servletContext);
            }

            if (!commandName.equals("registerUser")) {

                session.setAttribute("Admin", session.getAttribute("Admin"));
                servletContext.setAttribute("login", session.getAttribute("Admin"));
                servletContext.setAttribute("Admin", session.getAttribute("Admin"));
                User adminUser = userService.findByLogin((String) session.getAttribute("Admin"));
                Integer numberOfRows = userService.getNumberOfRows();
                Integer nOfPages = (double) (numberOfRows / recordsPerPage) < 1 ? 1 : numberOfRows / recordsPerPage + ((numberOfRows % recordsPerPage) > 0 ? 1 : 0);
//            servletContext.setAttribute("Admin", session.getAttribute("Admin"));
                servletContext.setAttribute("users", userService.getAllUser(adminUser.getId(), currentPage,
                        recordsPerPage));
                logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
                servletContext.setAttribute("noOfPages", nOfPages);
                servletContext.setAttribute("currentPage", currentPage);
                servletContext.setAttribute("recordsPerPage", recordsPerPage);
            } else {
                servletContext.setAttribute("login", req.getParameter("login"));
                servletContext.setAttribute("password", req.getParameter("password"));

            }
        } catch (DBException e) {
            session.setAttribute("errMessage", e.getMessage());
            address = req.getContextPath() + "/jsp/authError.jsp";

        }

        resp.sendRedirect(address);

    }
}
