package Controller.Subject;

import Controller.Command;
import DAO.SubjectDaoImpl;
import DAO.UserDaoImpl;
import org.apache.log4j.Logger;
import service.SubjectService;
import service.SubjectServiceImpl;

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

@WebServlet("/adminSubject")
public class AdminSubjectServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(AdminSubjectServlet.class);

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
        if(req.getParameter("recordsPerPage")!= null){
        currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        if(req.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }
        String commandName = req.getParameter("command");
        Command command = AdminSubjectCommandContainer.getCommand(commandName);
        String address = req.getContextPath() + "/jsp/admSubjectMenager.jsp";

        try {
            if (command != null) {

            address = command.execute(req, resp, servletContext);
     }

         SubjectService subjectService = new SubjectServiceImpl();


        HttpSession session = req.getSession();//create session
        if (session.getAttribute("Admin") !=null) {
            //        session.setAttribute("Admin", session.getAttribute("login"));
            servletContext.setAttribute("login", session.getAttribute("Admin"));
            servletContext.setAttribute("Admin", session.getAttribute("Admin"));
        } else {
            servletContext.setAttribute("login", session.getAttribute("User"));
            servletContext.setAttribute("Admin", session.getAttribute("User"));
            servletContext.setAttribute("UserUser", session.getAttribute("UserUser"));
        }

        Integer numberOfRows = subjectService.getNumberOfRows();
        Integer nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);

        servletContext.setAttribute("subjects", subjectService.getAllSubjects(currentPage,
                recordsPerPage));
        logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
        servletContext.setAttribute("noOfPages", nOfPages);
        servletContext.setAttribute("currentPage", currentPage);
        servletContext.setAttribute("recordsPerPage", recordsPerPage);
        } catch (DBException e) {
            address = req.getContextPath() + "/jsp/authError.jsp";
            e.printStackTrace();
        }

        resp.sendRedirect(address);

    }
}
