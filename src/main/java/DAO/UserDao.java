package DAO;

import model.User;
import Exception.DBException;
import java.util.List;
import Exception.DBException;
public interface UserDao {
    User findById(int id);

    User findByEmail(String email);

    User findByLogin(String login) throws DBException;

    boolean save(User user) throws DBException;

    boolean update(User user) throws DBException;

    boolean delete(int id) throws DBException;

    List<User> getAllUser(int id, int currentPage, int numOfRecords) throws DBException;
    Integer getNumberOfRows() throws DBException;
}
