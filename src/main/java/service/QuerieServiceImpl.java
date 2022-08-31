package service;

import DAO.QuerieDao;
import DAO.QuerieDaoImpl;
import DAO.TestDao;
import DAO.TestDaoImpl;
import model.Querie;
import Exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuerieServiceImpl implements QuerieService{
    private final static Logger logger = LoggerFactory.getLogger(QuerieServiceImpl.class);

    private final QuerieDao querieDao = new QuerieDaoImpl();

    @Override
    public Querie findById(int id) {
        return null;
    }

    @Override
    public boolean save(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Querie querie = new Querie();
        querie.setQuestion(req.getParameter("question"));
        querie.setTest_id(Integer.parseInt(req.getParameter("test_Id")));

        return querieDao.save(querie);
    }

    @Override
    public boolean update(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Querie querie = new Querie();

        querie.setId(Integer.parseInt(req.getParameter("querieId")));
        querie.setQuestion(req.getParameter("question"));
        querie.setTest_id(Integer.parseInt(req.getParameter("test_Id")));

        return querieDao.update(querie);
    }

    @Override
    public boolean delete(int id) throws DBException {
        return querieDao.delete(id);
    }

    @Override
    public List<Querie> getAllQueries(int id, int currentPage, int numOfRecords) throws DBException {
        return querieDao.getAllQueries(id,currentPage,numOfRecords);
    }

    @Override
    public Integer getNumberOfRows() throws DBException {
        return querieDao.getNumberOfRows();
    }
}
