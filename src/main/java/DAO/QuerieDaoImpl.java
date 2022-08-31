package DAO;

import model.Querie;
import model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DSInstance;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import Exception.DBException;

import static util.EmptyResources.close;

public class QuerieDaoImpl implements QuerieDao {
    private static final String SQL_SELECT_ALL_QUERIE = "SELECT * FROM QUERIE WHERE test_id = ?  LIMIT ?, ?";
    private static final String SQL_SELECT_QUERIE_ROWS_COUNT = "SELECT COUNT(id) AS cnt FROM QUERIE";
    private static final String SQL_INSERT_INTO_QUERIE =
            "INSERT into QUERIE  " +
                    "(question, test_id) " +
                    "values" +
                    " (?, ?) ";
    private static final String SQL_UPDATE_QUERIE =
            "UPDATE QUERIE SET " +
                    "question = ? " +
                    "where id = ?";
    private static final String SQL_DELETE_QUERIE = "DELETE FROM QUERIE WHERE id = ?";
    private static final DataSource ds = DSInstance.getInstance().getDs();
    private static final Logger logger = LoggerFactory.getLogger(QuerieDaoImpl.class);

    @Override
    public Querie findById(int id) {
        return null;
    }

    @Override
    public boolean save(Querie querie) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_QUERIE);
            pstmt.setString(1, querie.getQuestion());
            pstmt.setInt(2, querie.getTest_id());

            count = pstmt.executeUpdate();
            if (count > 0) {
                logger.info("querie = " + querie.getQuestion() + ". DB inserted");
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DBException(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return false;
    }

    @Override
    public boolean update(Querie querie) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            logger.info("connection ==> " + con);
            pstmt = con.prepareStatement(SQL_UPDATE_QUERIE);
            pstmt.setString(1, querie.getQuestion());
            pstmt.setInt(2, querie.getId());

            count = pstmt.executeUpdate();
            logger.info("update#Executed");
            if (count > 0) {
                logger.info("querie id = " + querie.getId() + ". DB updated");
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DBException(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return false;

    }

    @Override
    public boolean delete(int id) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            logger.info("connection ==> " + con);
            pstmt = con.prepareStatement(SQL_DELETE_QUERIE);

            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
            logger.info("delete#Executed");
            if (count > 0) {
                logger.info("querie id = " + id + ". DB deleted");
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DBException(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return false;

    }

    @Override
    public List<Querie> getAllQueries(int id, int currentPage, int recordsPerPage) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int start = currentPage * recordsPerPage - recordsPerPage;

        List<Querie> queries = new ArrayList<>();
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ALL_QUERIE);
            pstmt.setInt(1, id);
            pstmt.setInt(2, start);
            pstmt.setInt(3, recordsPerPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                queries.add(extractQuerie(rs));
            }
            logger.info("Selected queries ==> " + queries.size() + " counts.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DBException(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return queries;
    }

    @Override
    public Integer getNumberOfRows() throws DBException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        final int count;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_QUERIE_ROWS_COUNT);

            if (rs.next()) {
                logger.info("Selected querie count ==> " + rs.getInt("cnt") + " .");
                return rs.getInt("cnt");

            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DBException(e.getMessage());
        } finally {
            close(con, logger);
            close(stmt, logger);
            close(rs, logger);
        }
          logger.info("Error in cout select.");
        return 0;
    }

    private Querie extractQuerie(ResultSet rs) throws SQLException {
        Querie querie = new Querie();
        querie.setId(rs.getInt("id"));
        querie.setQuestion(rs.getString("question"));
        querie.setTest_id(rs.getInt("test_id"));
        return querie;
    }

}
