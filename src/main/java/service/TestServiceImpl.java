package service;

import DAO.SubjectDao;
import DAO.SubjectDaoImpl;
import DAO.TestDao;
import DAO.TestDaoImpl;
import model.Test;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestServiceImpl implements TestService{
    private final static Logger logger = Logger.getLogger(TestServiceImpl.class);

    private final TestDao testDao = new TestDaoImpl();
    @Override
    public Test findById(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }

    @Override
    public Test findByTask(String email) {
        return null;
    }

    @Override
    public Test findByName(String login) {
        return testDao.findByName(login);
    }

    @Override
    public boolean save(HttpServletRequest req, HttpServletResponse resp) {
        Test test = new Test();
        test.setName(req.getParameter("name"));
        test.setTask(req.getParameter("task"));
        test.setSubject_id(Integer.parseInt(req.getParameter("subject_Id")));
        test.setDifficulty(Integer.parseInt(req.getParameter("difficulty")));
        test.setPassingTimeMin(Short.parseShort(req.getParameter("passingTimeMin")));

        return testDao.save(test);
    }

    @Override
    public boolean update(HttpServletRequest req, HttpServletResponse resp) {
        Test test = new Test();

        test.setId(Integer.parseInt(req.getParameter("testId")));
        test.setName(req.getParameter("name"));
        test.setTask(req.getParameter("task"));
        test.setSubject_id(Integer.parseInt(req.getParameter("subject_Id")));
        test.setDifficulty(Integer.parseInt(req.getParameter("difficulty")));
        test.setPassingTimeMin(Short.parseShort(req.getParameter("passingTimeMin")));

        return testDao.update(test);
    }

    @Override
    public boolean delete(HttpServletRequest req, HttpServletResponse resp) {
        int testId = Integer.parseInt(req.getParameter("testId"));
        return testDao.delete(testId);
    }

    @Override
    public boolean insertUser_Test(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) {
        int user_Id = ((User) servletContext.getAttribute("UserUser")).getId();
        int test_Id =Integer.parseInt((String) servletContext.getAttribute("test_Id"));
        return testDao.insertUser_Test(user_Id, test_Id);
    }

    @Override
    public boolean User_Tests_Finished_Upd(int userId) {
        return testDao.User_Tests_Finished_Upd(userId);
    }

    @Override
    public List<Test> getAllUserTests(int user_Id, int subject_id, int currentPage, int numOfRecords) {
        return testDao.getAllUserTests(user_Id, subject_id, currentPage, numOfRecords);
    }

    @Override
    public List<Test> getAllTests(HttpServletRequest req, HttpServletResponse resp, int currentPage, int recordsPerPage) {
        int subject_id = Integer.parseInt(req.getParameter("subject_Id"));
        return testDao.getAllTests(subject_id, currentPage, recordsPerPage );
    }

    @Override
    public Integer getNumberOfRows() {
        return testDao.getNumberOfRows();
    }
}
