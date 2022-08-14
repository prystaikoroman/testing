package DAO;

import model.User;

import java.util.List;

public interface userDao {
    User findById(int id);

    User findByEmail(String email);

    boolean save(User user);

    boolean update(User user);

    boolean delete(int id);

    List<User> getAllUser();
}
