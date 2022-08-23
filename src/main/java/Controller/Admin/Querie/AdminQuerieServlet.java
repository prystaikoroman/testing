package Controller.Admin.Querie;

import Controller.Command;
import DAO.QuerieDaoImpl;
import DAO.TestDaoImpl;
import org.apache.log4j.Logger;
import service.QuerieService;
import service.QuerieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        getServletContext().setAttribute("test_Id", req.getParameter("test_Id"));
        getServletContext().setAttribute("subject_Id", req.getParameter("subject_Id"));

        int currentPage = 1;
        int recordsPerPage = 5;
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

            address = command.execute(req, resp);
        }

        QuerieService querieService = new QuerieServiceImpl();
        HttpSession session = req.getSession();//create session
        if (session.getAttribute("Admin") !=null) {
            //        session.setAttribute("Admin", session.getAttribute("login"));
            getServletContext().setAttribute("login", session.getAttribute("Admin"));
            getServletContext().setAttribute("Admin", session.getAttribute("Admin"));
        } else {
            getServletContext().setAttribute("login", session.getAttribute("User"));
            getServletContext().setAttribute("Admin", session.getAttribute("User"));
        }

        Integer numberOfRows = querieService.getNumberOfRows();
        Integer nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);

        getServletContext().setAttribute("queries", querieService.getAllQueries(Integer.parseInt(req.getParameter("test_Id")), currentPage,
                recordsPerPage));
        logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
        getServletContext().setAttribute("noOfPages", nOfPages);
        getServletContext().setAttribute("currentPage", currentPage);
        getServletContext().setAttribute("recordsPerPage", recordsPerPage);



        resp.sendRedirect(address);

    }
}
