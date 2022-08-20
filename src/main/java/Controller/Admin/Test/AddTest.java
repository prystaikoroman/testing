package Controller.Admin.Test;

import Controller.Command;
import DAO.SubjectDaoImpl;
import DAO.TestDaoImpl;
import model.Subject;
import model.Test;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTest implements Command {
    private final static Logger logger = Logger.getLogger(AddTest.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        TestDaoImpl testDao = new TestDaoImpl();
        Test test = new Test();
        test.setName(req.getParameter("name"));
        test.setTask(req.getParameter("task"));
        test.setSubject_id(Integer.parseInt(req.getParameter("subject_Id")));
        test.setDifficulty(Integer.parseInt(req.getParameter("difficulty")));
        test.setPassingTimeMin(Short.parseShort(req.getParameter("passingTimeMin")));
        testDao.save(test);
        return req.getContextPath()+"/jsp/admTestMenager.jsp";

    }
}
