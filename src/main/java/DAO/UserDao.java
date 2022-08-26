package DAO;

import model.User;

import java.util.List;
import Exception.DBException;
public interface UserDao {
    User findById(int id);

    User findByEmail(String email);

    User findByLogin(String login);

    boolean save(User user) throws DBException;

    boolean update(User user);

    boolean delete(int id);

    List<User> getAllUser(int id, int currentPage, int numOfRecords);
    Integer getNumberOfRows();
}
