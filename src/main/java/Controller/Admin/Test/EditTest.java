package Controller.Admin.Test;

import Controller.Command;
import DAO.SubjectDaoImpl;
import DAO.TestDaoImpl;
import model.Subject;
import model.Test;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditTest implements Command {
    private final static Logger logger = Logger.getLogger(EditTest.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        TestDaoImpl testDao = new TestDaoImpl();
        Test test = new Test();

        test.setId(Integer.parseInt(req.getParameter("testId")));
        test.setName(req.getParameter("name"));
        test.setTask(req.getParameter("task"));
        test.setSubject_id(Integer.parseInt(req.getParameter("subject_Id")));
        test.setDifficulty(Integer.parseInt(req.getParameter("difficulty")));
        test.setPassingTimeMin(Short.parseShort(req.getParameter("passingTimeMin")));

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        testDao.update(test);

        return req.getContextPath()+"/jsp/admTestMenager.jsp";
    }
}
