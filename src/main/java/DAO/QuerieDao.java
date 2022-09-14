package DAO;

import model.Querie;
import model.Test;

import java.util.List;
import Exception.DBException;
public interface QuerieDao {
    Querie findById(int id);

    boolean save(Querie querie) throws DBException;

    boolean update(Querie querie) throws DBException;

    boolean delete(int id) throws DBException;

    List<Querie> getAllQueries(int id, int currentPage, int numOfRecords) throws DBException;
    Integer getNumberOfRows() throws DBException;

    Integer getTestQueriesNumberOfRows(int id) throws DBException;
}
