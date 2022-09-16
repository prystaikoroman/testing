package com.testing.service;

import com.testing.model.Querie;
import com.testing.Exception.DBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface QuerieService {
    Querie findById(int id);

    boolean save(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean update(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean delete(int id) throws DBException;

    List<Querie> getAllQueries(int id, int currentPage, int numOfRecords) throws DBException;
    Integer getNumberOfRows() throws DBException;
    Integer getTestQueriesNumberOfRows(int id) throws DBException;
}
