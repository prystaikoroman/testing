package Controller.Admin.Test;

import Controller.Command;
import DAO.SubjectDaoImpl;
import DAO.TestDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTest implements Command {
    private final static Logger logger = Logger.getLogger(DeleteTest.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        TestDaoImpl testDao = new TestDaoImpl();

        testDao.delete(Integer.parseInt(req.getParameter("testId")));
        return req.getContextPath()+"/jsp/admTestMenager.jsp";
    }
}
