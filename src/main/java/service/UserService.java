package service;

import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    User findById(int id);

    User findByEmail(String email);

    User findByLogin(String login);

    boolean save(HttpServletRequest req, HttpServletResponse resp);

    boolean update(HttpServletRequest req, HttpServletResponse resp);

    boolean delete(HttpServletRequest req, HttpServletResponse resp);

    List<User> getAllUser(int id, int currentPage, int numOfRecords);

    Integer getNumberOfRows();

}
