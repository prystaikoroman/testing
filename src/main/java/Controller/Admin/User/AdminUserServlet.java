package Controller.Admin.User;

import Controller.Command;
import DAO.UserDaoImpl;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Front Controller Pattern Servlet

@WebServlet("/adminUser")
public class AdminUserServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(AdminUserServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doPost");
        process( req,  resp);
     }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("entered#doGet");
        process( req,  resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        logger.info("entered#process");
        int currentPage = 1;
        int recordsPerPage = 5;
        currentPage = Integer.parseInt(req.getParameter("currentPage"));
        recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));

        String commandName = req.getParameter("command");
        Command command = AdminUserCommandContainer.getCommand(commandName);
         String address = req.getContextPath()+"/jsp/admUserMenager.jsp";

         if(command!=null){

             address= command.execute(req, resp);
         }

        UserDaoImpl userDao = new UserDaoImpl();
        HttpSession session = req.getSession();//create session
//        session.setAttribute("Admin", session.getAttribute("login"));
        getServletContext().setAttribute("login", session.getAttribute("Admin"));

        User adminUser = userDao.findByLogin((String) session.getAttribute("Admin"));
        Integer numberOfRows = userDao.getNumberOfRows();
        Integer nOfPages = (double)(numberOfRows / recordsPerPage)<1 ? 1: numberOfRows / recordsPerPage +( (numberOfRows % recordsPerPage)>0?1:0);
        getServletContext().setAttribute("Admin", session.getAttribute("Admin"));
        getServletContext().setAttribute("users", userDao.getAllUser(adminUser.getId(), currentPage,
                recordsPerPage));
        logger.info("noOfPages=" + nOfPages + " currentPage="+ currentPage+ " recordsPerPage="+  recordsPerPage);
        getServletContext().setAttribute("noOfPages", nOfPages);
        getServletContext().setAttribute("currentPage", currentPage);
        getServletContext().setAttribute("recordsPerPage", recordsPerPage);

        resp.sendRedirect(address);

    }
}
