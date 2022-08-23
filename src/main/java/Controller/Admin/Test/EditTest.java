package Controller.Admin.Test;

import Controller.Command;
import DAO.SubjectDaoImpl;
import DAO.TestDaoImpl;
import model.Subject;
import model.Test;
import org.apache.log4j.Logger;
import service.TestService;
import service.TestServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditTest implements Command {
    private final static Logger logger = Logger.getLogger(EditTest.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        TestService testService = new TestServiceImpl();
//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        testService.update(req, resp);

        return req.getContextPath()+"/jsp/admTestMenager.jsp";
    }
}
