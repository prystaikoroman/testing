package com.testing.DAO;

import com.testing.model.Subject;

import java.util.List;
import com.testing.Exception.DBException;
public interface SubjectDao {
    Subject findById(int id);

    Subject findByName(String name) throws DBException;

    boolean save(Subject subject) throws DBException;

    boolean update(Subject subject) throws DBException;

    boolean delete(int id) throws DBException;

    List<Subject> getAllSubjects(int currentPage, int numOfRecords) throws DBException;

    Integer getNumberOfRows() throws DBException;
}
