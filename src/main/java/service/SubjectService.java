package service;

import model.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SubjectService {
    Subject findById(int id);

    Subject findByName(String name);

    boolean save(HttpServletRequest req, HttpServletResponse resp);

    boolean update(HttpServletRequest req, HttpServletResponse resp);

    boolean delete(HttpServletRequest req, HttpServletResponse resp);

    List<Subject> getAllSubjects(int currentPage, int numOfRecords);

    Integer getNumberOfRows();

}
