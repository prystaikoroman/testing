package DAO;

import model.Subject;
import model.User;

import java.util.List;
import Exception.DBException;
public interface SubjectDao {
    Subject findById(int id);

    Subject findByName(String name) throws DBException;

    boolean save(Subject subject) throws DBException;

    boolean update(Subject subject) throws DBException;

    boolean delete(int id) throws DBException;

    List<Subject> getAllSubjects(int currentPage, int numOfRecords) throws DBException;

    Integer getNumberOfRows() throws DBException;
}
