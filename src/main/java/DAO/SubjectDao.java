package DAO;

import model.Subject;
import model.User;

import java.util.List;

public interface SubjectDao {
    Subject findById(int id);

    Subject findByName(String name);

    boolean save(Subject subject);

    boolean update(Subject subject);

    boolean delete(int id);

    List<Subject> getAllSubjects(int currentPage, int numOfRecords);

    Integer getNumberOfRows();
}
