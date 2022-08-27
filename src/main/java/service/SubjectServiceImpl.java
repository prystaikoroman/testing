package service;

import DAO.SubjectDao;
import DAO.SubjectDaoImpl;
import DAO.UserDao;
import DAO.UserDaoImpl;
import model.Subject;
import org.apache.log4j.Logger;
import Exception.DBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubjectServiceImpl implements SubjectService{
    private final static Logger logger = Logger.getLogger(SubjectServiceImpl.class);

    private final SubjectDao subjectDao = new SubjectDaoImpl();

    @Override
    public Subject findById(int id) {
        return null;
    }

    @Override
    public Subject findByName(String name) throws DBException {
        logger.info("entered#findByName");
        return subjectDao.findByName(name);
    }

    @Override
    public boolean save(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        logger.info("entered#save");
        Subject subject = new Subject();
        subject.setName(req.getParameter("name"));
        return subjectDao.save(subject);
    }

    @Override
    public boolean update(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        logger.info("entered#update");
        Subject subject = new Subject();

        subject.setId(Integer.parseInt(req.getParameter("subjectId")));
        subject.setName(req.getParameter("name"));
        return subjectDao.update(subject);
    }

    @Override
    public boolean delete(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        logger.info("entered#delete");
        int subjectId = Integer.parseInt(req.getParameter("subjectId"));
        return subjectDao.delete(subjectId);
    }

    @Override
    public List<Subject> getAllSubjects(int currentPage, int numOfRecords) throws DBException {
        logger.info("entered#getAllSubjects");
        return subjectDao.getAllSubjects(currentPage, numOfRecords);
    }

    @Override
    public Integer getNumberOfRows() throws DBException {
        logger.info("entered#getNumberOfRows");
        return subjectDao.getNumberOfRows();
    }
}
