package Controller.Admin.Querie;

import Controller.Command;
import DAO.AnswerDao;
import DAO.AnswerDaoImpl;
import DAO.QuerieDaoImpl;
import DAO.TestDaoImpl;
import model.Querie;
import org.apache.log4j.Logger;
import service.AnswerService;
import service.AnswerServiceImpl;
import service.QuerieService;
import service.QuerieServiceImpl;

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
    private final static Logger logger = Logger.getLogger(AdminQuerieServlet.class);

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
        servletContext.setAttribute("test_Id", req.getParameter("test_Id"));
        servletContext.setAttribute("subject_Id", req.getParameter("subject_Id"));

        int currentPage = 1;
        int recordsPerPage = 5;
        Integer nOfPages =1;
        if(req.getParameter("currentPage")!= null){
        currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        if(req.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }
        String commandName = req.getParameter("command");
        Command command = AdminQuerieCommandContainer.getCommand(commandName);
        String address = req.getContextPath() + "/jsp/admQuerieMenager.jsp";

        if (command != null) {
            try {
            address = command.execute(req, resp, servletContext);
            } catch (DBException e) {
                address = req.getContextPath() + "/jsp/authError.jsp";
                e.printStackTrace();
            }

        }

        QuerieService querieService = new QuerieServiceImpl();
        HttpSession session = req.getSession();//create session
        if (session.getAttribute("Admin") !=null) {
            //        session.setAttribute("Admin", session.getAttribute("login"));
            servletContext.setAttribute("login", session.getAttribute("Admin"));
            servletContext.setAttribute("Admin", session.getAttribute("Admin"));
            Integer numberOfRows = querieService.getNumberOfRows();
             nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);

            servletContext.setAttribute("queries", querieService.getAllQueries(Integer.parseInt(req.getParameter("test_Id")), currentPage,
                    recordsPerPage));
            logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
            servletContext.setAttribute("noOfPages", nOfPages);
            servletContext.setAttribute("currentPage", currentPage);
            servletContext.setAttribute("recordsPerPage", recordsPerPage);
        } else {
            servletContext.setAttribute("login", session.getAttribute("User"));
            servletContext.setAttribute("Admin", session.getAttribute("User"));
            Integer numberOfRows = querieService.getNumberOfRows();
             nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);

            servletContext.setAttribute("queries", querieService.getAllQueries(Integer.parseInt(req.getParameter("test_Id")), currentPage,
                    recordsPerPage));

            List<Querie> queries = (List<Querie>) servletContext.getAttribute("queries");
//            Querie[] queries = (Querie[]) servletContext.getAttribute("queries");

            AnswerService answerService = new AnswerServiceImpl();
            if(queries.size()>0) {
                servletContext.setAttribute("answers", answerService.getAllAnswers(queries.get(0).getId(), 1,
                        150));
            }
            logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
            servletContext.setAttribute("noOfPages", nOfPages);
            servletContext.setAttribute("currentPage", currentPage);
            servletContext.setAttribute("recordsPerPage", recordsPerPage);
        }




        resp.sendRedirect(address);

    }
}
