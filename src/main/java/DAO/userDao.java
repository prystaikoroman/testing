package DAO;

import model.User;

import java.security.Principal;
import java.util.List;

public interface userDao {
    User findById(int id);

    User findByEmail(String email);

    void save(User user);

    void update(User user);

    List<User> getAllUser();
}
