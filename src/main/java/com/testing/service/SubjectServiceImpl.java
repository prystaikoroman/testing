package com.testing.service;

import com.testing.DAO.SubjectDao;
import com.testing.DAO.SubjectDaoImpl;
import com.testing.model.Subject;
import com.testing.Exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.util.DSInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;

public class SubjectServiceImpl implements SubjectService{
    private final static Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
    private static final DataSource ds = DSInstance.getInstance().getDs();

    private final SubjectDao subjectDao = new SubjectDaoImpl(ds);

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
