package com.testing.service;

import com.testing.DAO.UserDao;
import com.testing.DAO.UserDaoImpl;
import com.testing.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;
import com.testing.Exception.DBException;
import com.testing.util.DSInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService{
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final DataSource ds = DSInstance.getInstance().getDs();
    private final UserDao userDao = new UserDaoImpl(ds);
    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByLogin(String login) throws DBException {
        logger.info("entered#findByLogin");
        return userDao.findByLogin(login);
    }

    @Override
    public boolean save(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        logger.info("entered#save");
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setLastname(req.getParameter("lastname"));
        user.setAdmin(req.getParameter("admin") != null && req.getParameter("admin").equals("on"));
        user.setLocked(req.getParameter("locked") != null && req.getParameter("locked").equals("on"));

        return userDao.save(user);
    }

    @Override
    public boolean update(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        logger.info("entered#update");
        User user = new User();

        user.setId(Integer.parseInt(req.getParameter("userId")));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setLastname(req.getParameter("lastname"));
        user.setAdmin(req.getParameter("admin") != null && req.getParameter("admin").equals("on"));
        user.setLocked(req.getParameter("locked") != null && req.getParameter("locked").equals("on"));

        return userDao.update(user);
    }

    @Override
    public boolean delete(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        logger.info("entered#delete");
        int userId = Integer.parseInt(req.getParameter("userId"));

        return userDao.delete(userId);
    }

    @Override
    public List<User> getAllUser(int id, int currentPage, int numOfRecords) throws DBException {
        logger.info("entered#getAllUser");
        return userDao.getAllUser(id, currentPage, numOfRecords);
    }

    @Override
    public Integer getNumberOfRows() throws DBException {
        logger.info("entered#getNumberOfRows");

        return userDao.getNumberOfRows();
    }
}
