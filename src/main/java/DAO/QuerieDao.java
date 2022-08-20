package DAO;

import model.Querie;
import model.Test;

import java.util.List;

public interface QuerieDao {
    Querie findById(int id);

    boolean save(Querie querie);

    boolean update(Querie querie);

    boolean delete(int id);

    List<Querie> getAllQueries(int id, int currentPage, int numOfRecords);
    Integer getNumberOfRows();
}
