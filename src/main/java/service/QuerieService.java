package service;

import model.Querie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface QuerieService {
    Querie findById(int id);

    boolean save(HttpServletRequest req, HttpServletResponse resp);

    boolean update(HttpServletRequest req, HttpServletResponse resp);

    boolean delete(int id);

    List<Querie> getAllQueries(int id, int currentPage, int numOfRecords);
    Integer getNumberOfRows();
}
