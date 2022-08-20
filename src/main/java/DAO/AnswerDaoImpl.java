package DAO;

import model.Answer;
import model.Querie;
import org.apache.log4j.Logger;
import util.DSInstance;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.EmptyResources.close;

public class AnswerDaoImpl implements AnswerDao {
    private static final String SQL_SELECT_ALL_ANSWER = "SELECT * FROM ANSWER WHERE querie_id = ?  LIMIT ?, ?";
    private static final String SQL_SELECT_ANSWER_ROWS_COUNT = "SELECT COUNT(id) AS cnt FROM ANSWER";
    private static final String SQL_INSERT_INTO_ANSWER =
            "INSERT into ANSWER  " +
                    "(answer, correct, querie_id) " +
                    "values" +
                    " (?, ?, ?) ";
    private static final String SQL_UPDATE_ANSWER =
            "UPDATE ANSWER SET " +
                    "answer = ?, correct = ? " +
                    "where id = ?";
    private static final String SQL_DELETE_ANSWER = "DELETE FROM ANSWER WHERE id = ?";
    private static final DataSource ds = DSInstance.getInstance().getDs();
    private static final Logger logger = Logger.getLogger(AnswerDaoImpl.class);

    @Override
    public Answer findById(int id) {
        return null;
    }

    @Override
    public boolean save(Answer answer) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_ANSWER);
            pstmt.setString(1, answer.getAnswer());
            pstmt.setBoolean(2, answer.isCorrect());
            pstmt.setInt(3, answer.getQuerie_id());

            count = pstmt.executeUpdate();
            if (count > 0) {
                logger.info("answer = " + answer.getAnswer() + ". DB inserted");
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
    public boolean update(Answer answer) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            logger.info("connection ==> " + con);
            pstmt = con.prepareStatement(SQL_UPDATE_ANSWER);
            pstmt.setString(1, answer.getAnswer());
            pstmt.setBoolean(2, answer.isCorrect());
            pstmt.setInt(3, answer.getId());

            count = pstmt.executeUpdate();
            logger.info("update#Executed");
            if (count > 0) {
                logger.info("Answer id = " + answer.getId() + ". DB updated");
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
            pstmt = con.prepareStatement(SQL_DELETE_ANSWER);

            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
            logger.info("delete#Executed");
            if (count > 0) {
                logger.info("Answer id = " + id + ". DB deleted");
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
    public List<Answer> getAllAnswers(int id, int currentPage, int recordsPerPage) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int start = currentPage * recordsPerPage - recordsPerPage;

        List<Answer> answers = new ArrayList<>();
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ALL_ANSWER);
            pstmt.setInt(1, id);
            pstmt.setInt(2, start);
            pstmt.setInt(3, recordsPerPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                answers.add(extractAnswer(rs));
            }
            logger.info("Selected Answers ==> " + answers.size() + " counts.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return answers;
    }

    @Override
    public Integer getNumberOfRows() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        final int count;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ANSWER_ROWS_COUNT);

            if (rs.next()) {
                logger.info("Selected Answer count ==> " + rs.getInt("cnt") + " .");
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

    private Answer extractAnswer(ResultSet rs) throws SQLException {
        Answer answer = new Answer();
        answer.setId(rs.getInt("id"));
        answer.setAnswer(rs.getString("answer"));
        answer.setCorrect(rs.getBoolean("correct"));
        answer.setQuerie_id(rs.getInt("querie_id"));
        return answer;
    }

}
