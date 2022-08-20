package DAO;

import model.Test;


import java.util.List;

public interface TestDao {
    Test findById(int id);

    Test findByTask(String email);

    Test findByName(String login);

    boolean save(Test test);

    boolean update(Test test);

    boolean delete(int id);

    List<Test> getAllTests(int id, int currentPage, int numOfRecords);
    Integer getNumberOfRows();
}
