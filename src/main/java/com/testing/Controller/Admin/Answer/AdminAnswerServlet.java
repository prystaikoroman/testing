package com.testing.Controller.Admin.Answer;

import com.testing.Controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.service.AnswerService;
import com.testing.service.AnswerServiceImpl;

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

@WebServlet("/adminAnswer")
public class AdminAnswerServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(AdminAnswerServlet.class);

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
        AnswerService  answerService= new AnswerServiceImpl();
        HttpSession session = req.getSession();//create session

        if(req.getParameter("currentPage")!= null){
        currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        if(req.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }
        String commandName = req.getParameter("command");
        Command command = AdminAnswerCommandContainer.getCommand(commandName);
        String address = req.getContextPath() + "/jsp/admAnswerMenager.jsp";
        try {
        if (command != null) {

                address = command.execute(req, resp, servletContext);
         }

        Integer numberOfRows = answerService.getNumberOfRows();
        Integer nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);

        servletContext.setAttribute("answers", answerService.getAllAnswers(Integer.parseInt(req.getParameter("querie_Id")), currentPage,
                recordsPerPage));
        logger.info("noOfPages=" + nOfPages + " currentPage=" + currentPage + " recordsPerPage=" + recordsPerPage);
        servletContext.setAttribute("noOfPages", nOfPages );
        servletContext.setAttribute("currentPage", currentPage);
        servletContext.setAttribute("recordsPerPage", recordsPerPage);
    } catch (DBException e) {
        session.setAttribute("errMessage", e.getMessage());
        address = req.getContextPath() + "/jsp/authError.jsp";
    }


        resp.sendRedirect(address);

    }
}
