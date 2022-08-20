package Controller.Admin.Test;

import Controller.Command;
import DAO.SubjectDaoImpl;
import DAO.TestDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Front Controller Pattern Servlet

@WebServlet("/adminTest")
public class AdminTestServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(AdminTestServlet.class);

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

        if (command != null) {

            address = command.execute(req, resp);
        }

         TestDaoImpl testDao = new TestDaoImpl();
        HttpSession session = req.getSession();//create session
//        session.setAttribute("Admin", session.getAttribute("login"));
        getServletContext().setAttribute("login", session.getAttribute("Admin"));

        Integer numberOfRows = testDao.getNumberOfRows();
        Integer nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);
        getServletContext().setAttribute("Admin", session.getAttribute("Admin"));
        getServletContext().setAttribute("tests", testDao.getAllTests(Integer.parseInt(req.getParameter("subject_Id")), currentPage,
                recordsPerPage));
        logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
        getServletContext().setAttribute("subject_Id", req.getParameter("subject_Id"));
        getServletContext().setAttribute("noOfPages", nOfPages);
        getServletContext().setAttribute("currentPage", currentPage);
        getServletContext().setAttribute("recordsPerPage", recordsPerPage);

        resp.sendRedirect(address);

    }
}
