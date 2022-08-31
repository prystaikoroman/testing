package Controller.Admin.Test;

import Controller.Command;
import DAO.SubjectDaoImpl;
import DAO.TestDaoImpl;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TestService;
import service.TestServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import Exception.DBException;
//Front Controller Pattern Servlet

@WebServlet("/adminTest")
public class AdminTestServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(AdminTestServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doPost command ==>" + req.getParameter("command"));
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doGet command ==>" + req.getParameter("command"));
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#process");
        ServletContext servletContext = getServletContext();
        int currentPage = 1;
        int recordsPerPage = 5;
        if(req.getParameter("currentPage")!= null){
        currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        if(req.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }
        String commandName = req.getParameter("command");
        Command command = AdminTestCommandContainer.getCommand(commandName);
        String address = req.getContextPath() + "/jsp/admTestMenager.jsp";


        TestService testService = new TestServiceImpl();
        HttpSession session = req.getSession();//create session
        try {
            if (command != null) {


                address = command.execute(req, resp, servletContext);
        }
        if (session.getAttribute("Admin") !=null) {
            //        session.setAttribute("Admin", session.getAttribute("login"));



            Integer numberOfRows = testService.getNumberOfRows();
            Integer nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);

            servletContext.setAttribute("tests", testService.getAllTests(req, resp, currentPage,
                    recordsPerPage));
            logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);

            servletContext.setAttribute("noOfPages", nOfPages);
            servletContext.setAttribute("currentPage", currentPage);
            servletContext.setAttribute("recordsPerPage", recordsPerPage);

        } else {

            int userId = ((User) servletContext.getAttribute("UserUser")).getId();
            boolean b = testService.User_Tests_Finished_Upd(userId);

            Integer numberOfRows = testService.getNumberOfRows();
            Integer nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);

            servletContext.setAttribute("tests",
                    testService.getAllUserTests(userId,
                                                Integer.parseInt(req.getParameter("subject_Id")),
                                                currentPage, recordsPerPage));
            logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);



            servletContext.setAttribute("noOfPages", nOfPages);
            servletContext.setAttribute("currentPage", currentPage);
            servletContext.setAttribute("recordsPerPage", recordsPerPage);
        }
    } catch (DBException e) {
            session.setAttribute("errMessage", e.getMessage());
            address = req.getContextPath() + "/jsp/authError.jsp";
    }

        resp.sendRedirect(address);

    }
}
