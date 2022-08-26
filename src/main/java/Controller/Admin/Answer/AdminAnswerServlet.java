package Controller.Admin.Answer;

import Controller.Command;
import DAO.AnswerDaoImpl;
import org.apache.log4j.Logger;
import service.AnswerService;
import service.AnswerServiceImpl;

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

@WebServlet("/adminAnswer")
public class AdminAnswerServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(AdminAnswerServlet.class);

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
        ServletContext servletContext = getServletContext();
        if(req.getParameter("currentPage")!= null){
        currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        if(req.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }
        String commandName = req.getParameter("command");
        Command command = AdminAnswerCommandContainer.getCommand(commandName);
        String address = req.getContextPath() + "/jsp/admAnswerMenager.jsp";

        if (command != null) {
            try {
                address = command.execute(req, resp, servletContext);
            } catch (DBException e) {
                address = req.getContextPath() + "/jsp/authError.jsp";
                e.printStackTrace();
            }
        }

        AnswerService  answerService= new AnswerServiceImpl();
        HttpSession session = req.getSession();//create session
        if (session.getAttribute("Admin") !=null) {
            //        session.setAttribute("Admin", session.getAttribute("login"));
            servletContext.setAttribute("login", session.getAttribute("Admin"));
            servletContext.setAttribute("Admin", session.getAttribute("Admin"));
        } else {
            servletContext.setAttribute("login", session.getAttribute("User"));
            servletContext.setAttribute("Admin", session.getAttribute("User"));
        }

        Integer numberOfRows = answerService.getNumberOfRows();
        Integer nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);

        servletContext.setAttribute("answers", answerService.getAllAnswers(Integer.parseInt(req.getParameter("querie_Id")), currentPage,
                recordsPerPage));
        logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
        servletContext.setAttribute("noOfPages", nOfPages );
        servletContext.setAttribute("currentPage", currentPage);
        servletContext.setAttribute("recordsPerPage", recordsPerPage);

        servletContext.setAttribute("querie_Id", req.getParameter("querie_Id"));
        servletContext.setAttribute("test_Id", req.getParameter("test_Id"));
        servletContext.setAttribute("subject_Id", req.getParameter("subject_Id"));


        resp.sendRedirect(address);

    }
}
