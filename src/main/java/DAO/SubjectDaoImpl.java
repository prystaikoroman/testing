package DAO;

import model.Subject;
import model.User;
import org.apache.log4j.Logger;
import util.DSInstance;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Exception.DBException;
import static util.EmptyResources.close;

public class SubjectDaoImpl implements SubjectDao {
    private static final String SQL_SELECT_ALL_SUBJECT = "SELECT * FROM SUBJECT  LIMIT ?, ?";
    private static final String SQL_SELECT_SUBJECT_BY_NAME = "SELECT * FROM SUBJECT WHERE name = ? ";
    private static final String SQL_SELECT_SUBJECT_ROWS_COUNT = "SELECT COUNT(id) AS cnt FROM SUBJECT";
    private static final String SQL_INSERT_INTO_SUBJECT =
            "INSERT into SUBJECT  " +
                    "(name) " +
                    "values" +
                    " (?) ";
    private static final String SQL_UPDATE_SUBJECT =
            "UPDATE SUBJECT SET " +
                    "name = ?" +
                    "where id = ?";
    private static final String SQL_DELETE_SUBJECT = "DELETE FROM SUBJECT WHERE id = ?";
    private static final DataSource ds = DSInstance.getInstance().getDs();
    private static final Logger logger = Logger.getLogger(SubjectDaoImpl.class);



    @Override
    public Subject findById(int id) {
        return null;
    }


    @Override
    public Subject findByName(String name) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Subject subject = null;
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_SUBJECT_BY_NAME);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                subject = extractSubject(rs);
                return subject;
            }
            logger.info("Selected subject ==> " + subject + " .");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DBException(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return subject;

    }

    @Override
    public boolean save(Subject subject) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_SUBJECT);
            pstmt.setString(1, subject.getName());

            count = pstmt.executeUpdate();
            if (count > 0) {
                logger.info("subject = " + subject.getName() + ". DB inserted");
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
    public boolean update(Subject subject) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            logger.info("connection ==> " + con);
            pstmt = con.prepareStatement(SQL_UPDATE_SUBJECT);
            pstmt.setString(1, subject.getName());
            pstmt.setInt(2, subject.getId());
            count = pstmt.executeUpdate();
            logger.info("update#Executed");
            if (count > 0) {
                logger.info("subject = " + subject.getName() + ". DB updated");
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
            pstmt = con.prepareStatement(SQL_DELETE_SUBJECT);

            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
            logger.info("delete#Executed");
            if (count > 0) {
                logger.info("subject id = " + id + ". DB deleted");
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
    public List<Subject> getAllSubjects( int currentPage, int recordsPerPage) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int start = currentPage * recordsPerPage - recordsPerPage;

        List<Subject> subjects = new ArrayList<>();
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ALL_SUBJECT);
            pstmt.setInt(1, start);
            pstmt.setInt(2, recordsPerPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                subjects.add(extractSubject(rs));
            }
            logger.info("Selected subjects ==> " + subjects.size() + " counts.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DBException(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return subjects;
    }

    @Override
    public Integer getNumberOfRows() throws DBException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Subject subject = null;
        final int count;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_SUBJECT_ROWS_COUNT);

            if (rs.next()) {
                logger.info("Selected subject count ==> " + rs.getInt("cnt") + " .");
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

    private Subject extractSubject(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getInt("id"));
        subject.setName(rs.getString("name"));
         return subject;
    }

}
