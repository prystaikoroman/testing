package service;

import model.Subject;
import Exception.DBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SubjectService {
    Subject findById(int id);

    Subject findByName(String name) throws DBException;

    boolean save(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean update(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    boolean delete(HttpServletRequest req, HttpServletResponse resp) throws DBException;

    List<Subject> getAllSubjects(int currentPage, int numOfRecords) throws DBException;

    Integer getNumberOfRows() throws DBException;

}
