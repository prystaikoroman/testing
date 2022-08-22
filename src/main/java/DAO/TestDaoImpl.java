package DAO;

import model.Test;
import org.apache.log4j.Logger;
import util.DSInstance;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.EmptyResources.close;

public class TestDaoImpl implements TestDao {
    private static final String SQL_SELECT_ALL_TEST = "SELECT * FROM TEST WHERE subject_id = ?  LIMIT ?, ?";
    private static final String SQL_SELECT_ALL_USER_TEST =
            "SELECT * FROM TEST as t left outer join USER_TEST ut on ut.test_id = t.id and ut.user_id =? WHERE subject_id = ?   LIMIT ?, ?";
    private static final String SQL_STORED_USER_TESTS_FINISHED_UPD = "CALL user_tests_finished_upd(?)";
    private static final String SQL_SELECT_TEST_BY_NAME = "SELECT * FROM TEST WHERE name = ? ";
    private static final String SQL_SELECT_TEST_ROWS_COUNT = "SELECT COUNT(id) AS cnt FROM TEST";
    private static final String SQL_INSERT_INTO_TEST =
            "INSERT into TEST  " +
                    "(task, subject_id, name, difficulty, passingTimeMin) " +
                    "values" +
                    " (?, ?, ?, ?, ?) ";
    private static final String SQL_UPDATE_TEST =
            "UPDATE TEST SET " +
                    "task = ?, name = ?, difficulty = ?, passingTimeMin = ? " +
                    "where id = ?";
    private static final String SQL_DELETE_TEST = "DELETE FROM TEST WHERE id = ?";
    private static final DataSource ds = DSInstance.getInstance().getDs();
    private static final Logger logger = Logger.getLogger(TestDaoImpl.class);

    @Override
    public boolean User_Tests_Finished_Upd(int user_id) {
        Connection con = null;
        CallableStatement cstmt = null;

        final boolean executed;
        try {
            con = ds.getConnection();
            cstmt = con.prepareCall(SQL_STORED_USER_TESTS_FINISHED_UPD);
            cstmt.setInt(1, user_id);

            executed = cstmt.execute();
            if (executed) {

                return true;
            }
            logger.info("User_Tests_Finished_Upd was updated ==> " + executed + " .");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(cstmt, logger);

        }
        return false;
    }

    @Override
    public Test findById(int id) {
        return null;
    }

    @Override
    public Test findByTask(String task) {
        return null;
    }

    @Override
    public Test findByName(String name) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Test test = null;
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_TEST_BY_NAME);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                test = extractTest(rs);
                return test;
            }
            logger.info("Selected test ==> " + test + " .");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return test;

    }

    @Override
    public boolean save(Test test) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_TEST);
            pstmt.setString(1, test.getTask());
            pstmt.setInt(2, test.getSubject_id());
            pstmt.setString(3, test.getName());
            pstmt.setInt(4, test.getDifficulty());
            pstmt.setShort(5, test.getPassingTimeMin());


            count = pstmt.executeUpdate();
            if (count > 0) {
                logger.info("test = " + test.getName() + ". DB inserted");
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return false;
    }

    @Override
    public boolean update(Test test) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            logger.info("connection ==> " + con);
            pstmt = con.prepareStatement(SQL_UPDATE_TEST);
            pstmt.setString(1, test.getTask());
            pstmt.setString(2, test.getName());
            pstmt.setInt(3, test.getDifficulty());
            pstmt.setShort(4, test.getPassingTimeMin());
            pstmt.setInt(5, test.getId());

            count = pstmt.executeUpdate();
            logger.info("update#Executed");
            if (count > 0) {
                logger.info("test = " + test.getName() + ". DB updated");
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return false;

    }

    @Override
    public boolean delete(int id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            logger.info("connection ==> " + con);
            pstmt = con.prepareStatement(SQL_DELETE_TEST);

            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
            logger.info("delete#Executed");
            if (count > 0) {
                logger.info("test id = " + id + ". DB deleted");
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return false;

    }

    @Override
    public List<Test> getAllTests(int id, int currentPage, int recordsPerPage) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int start = currentPage * recordsPerPage - recordsPerPage;

        List<Test> tests = new ArrayList<>();
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ALL_TEST);
            pstmt.setInt(1, id);
            pstmt.setInt(2, start);
            pstmt.setInt(3, recordsPerPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tests.add(extractTest(rs));
            }
            logger.info("Selected tests ==> " + tests.size() + " counts.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return tests;
    }

    @Override
    public List<Test> getAllUserTests(int user_id, int subject_id, int currentPage, int recordsPerPage) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int start = currentPage * recordsPerPage - recordsPerPage;

        List<Test> tests = new ArrayList<>();
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ALL_USER_TEST);
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, subject_id);
            pstmt.setInt(3, start);
            pstmt.setInt(4, recordsPerPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tests.add(extractTest(rs));
            }
            logger.info("Selected tests ==> " + tests.size() + " counts.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return tests;
    }

    @Override
    public Integer getNumberOfRows() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Test test = null;
        final int count;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_TEST_ROWS_COUNT);

            if (rs.next()) {
                logger.info("Selected test count ==> " + rs.getInt("cnt") + " .");
                return rs.getInt("cnt");

            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(stmt, logger);
            close(rs, logger);
        }
          logger.info("Error in cout select.");
        return 0;
    }

    private Test extractTest(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt("id"));
        test.setTask(rs.getString("task"));
        test.setSubject_id(rs.getInt("subject_id"));
        test.setName(rs.getString("name"));
        test.setDifficulty(rs.getInt("difficulty"));
        test.setPassingTimeMin(rs.getShort("passingTimeMin"));

        return test;
    }

}
