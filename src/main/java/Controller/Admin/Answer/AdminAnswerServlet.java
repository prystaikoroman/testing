package Controller.Admin.Answer;

import Controller.Command;
import DAO.AnswerDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

            address = command.execute(req, resp);
        }

        AnswerDaoImpl answerDao = new AnswerDaoImpl();
        HttpSession session = req.getSession();//create session
//        session.setAttribute("Admin", session.getAttribute("login"));
        getServletContext().setAttribute("login", session.getAttribute("Admin"));

        Integer numberOfRows = answerDao.getNumberOfRows();
        Integer nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);
        getServletContext().setAttribute("Admin", session.getAttribute("Admin"));
        getServletContext().setAttribute("answers", answerDao.getAllAnswers(Integer.parseInt(req.getParameter("querie_Id")), currentPage,
                recordsPerPage));
        logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
        getServletContext().setAttribute("noOfPages", nOfPages );
        getServletContext().setAttribute("currentPage", currentPage);
        getServletContext().setAttribute("recordsPerPage", recordsPerPage);

        getServletContext().setAttribute("querie_Id", req.getParameter("querie_Id"));
        getServletContext().setAttribute("test_Id", req.getParameter("test_Id"));
        getServletContext().setAttribute("subject_Id", req.getParameter("subject_Id"));


        resp.sendRedirect(address);

    }
}
