package com.testing.service;

import com.testing.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.testing.Exception.DBException;

public interface UserService {
    User findById(int id);

    User findByEmail(String email);

    User findByLogin(String login) throws DBException;

    boolean save(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean update(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean delete(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    List<User> getAllUser(int id, int currentPage, int numOfRecords) throws DBException;

    Integer getNumberOfRows() throws DBException;

}
