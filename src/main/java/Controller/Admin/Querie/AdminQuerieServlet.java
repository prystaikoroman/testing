package Controller.Admin.Querie;

import Controller.Command;
import DAO.AnswerDao;
import DAO.AnswerDaoImpl;
import DAO.QuerieDaoImpl;
import DAO.TestDaoImpl;
import model.Querie;
import model.User;
import model.User_Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Exception.DBException;
//Front Controller Pattern Servlet

@WebServlet("/adminQuerie")
public class AdminQuerieServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(AdminQuerieServlet.class);

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
        logger.info(req.getParameter("test_Id"));

        ServletContext servletContext = getServletContext();

        int currentPage = 1;
        int recordsPerPage = 5;
        int nOfPages = 1;
        if (req.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        if (req.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }
        String commandName = req.getParameter("command");
        Command command = AdminQuerieCommandContainer.getCommand(commandName);
        String address = req.getContextPath() + "/jsp/admQuerieMenager.jsp";

        QuerieService querieService = new QuerieServiceImpl();
        HttpSession session = req.getSession();//create session
        try {

            if (command != null) {

                address = command.execute(req, resp, servletContext);

            }
            Integer numberOfRows = querieService.getTestQueriesNumberOfRows(Integer.parseInt(req.getParameter("test_Id")));
            nOfPages = (double) (numberOfRows / recordsPerPage) < 1 ? 1 : numberOfRows / recordsPerPage + ((numberOfRows % recordsPerPage) > 0 ? 1 : 0);

            servletContext.setAttribute("queries", querieService.getAllQueries(Integer.parseInt(req.getParameter("test_Id")), currentPage,
                    recordsPerPage));
            logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
            servletContext.setAttribute("noOfPages", nOfPages);
            servletContext.setAttribute("currentPage", currentPage);
            servletContext.setAttribute("recordsPerPage", recordsPerPage);

            TestService testService = new TestServiceImpl();
            if (session.getAttribute("Admin") == null) {
//                int userId = ((User) servletContext.getAttribute("UserUser")).getId();
//                User_Test user_testById = testService.findUser_TestById(req, resp);
//                if (user_testById == null) {
//                    testService.insertUser_Test()
//                }
                List<Querie> queries = (List<Querie>) servletContext.getAttribute("queries");
//            Querie[] queries = (Querie[]) servletContext.getAttribute("queries");


                AnswerService answerService = new AnswerServiceImpl();
                if (queries.size() > 0) {
                    servletContext.setAttribute("answers", answerService.getAllAnswers_UserSubmition(queries.get(0).getId(), 1,
                            150));
                }
            }
        } catch (DBException e) {
            session.setAttribute("errMessage", e.getMessage());
            address = req.getContextPath() + "/jsp/authError.jsp";
        }

        resp.sendRedirect(address);

    }
}
