package service;

import Controller.Admin.User.AddUser;
import DAO.UserDao;
import DAO.UserDaoImpl;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import Exception.DBException;
public class UserServiceImpl implements UserService{
    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userDao = new UserDaoImpl();
    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
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
    public boolean update(HttpServletRequest req, HttpServletResponse resp) {
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
    public boolean delete(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#delete");
        int userId = Integer.parseInt(req.getParameter("userId"));

        return userDao.delete(userId);
    }

    @Override
    public List<User> getAllUser(int id, int currentPage, int numOfRecords) {
        logger.info("entered#getAllUser");
        return userDao.getAllUser(id, currentPage, numOfRecords);
    }

    @Override
    public Integer getNumberOfRows() {
        logger.info("entered#getNumberOfRows");

        return userDao.getNumberOfRows();
    }
}
