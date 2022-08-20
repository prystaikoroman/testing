package DAO;

import model.User;
import org.apache.log4j.Logger;
import util.DSInstance;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.EmptyResources.close;

public class UserDaoImpl implements UserDao {
    private static final String SQL_SELECT_ALL_USER = "SELECT * FROM USER WHERE id != ?  LIMIT ?, ?";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM USER WHERE login = ? ";
    private static final String SQL_SELECT_USER_ROWS_COUNT = "SELECT COUNT(id) AS cnt FROM USER";
    private static final String SQL_INSERT_INTO_USER =
            "INSERT into USER  " +
                    "(login, email, lastname, password, admin, locked) " +
                    "values" +
                    " (?, ?, ?, ?, ?, ?) ";
    private static final String SQL_UPDATE_USER =
            "UPDATE USER SET " +
                    "login = ?, email = ?, lastname = ?, password = ?, admin = ?, locked = ? " +
                    "where id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM USER WHERE id = ?";
    private static final DataSource ds = DSInstance.getInstance().getDs();
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);



    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            pstmt.setString(1, login);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
                return user;
            }
            logger.info("Selected user ==> " + user + " .");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return user;

    }

    @Override
    public boolean save(User user) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_USER);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getLastname());
            pstmt.setString(4, user.getPassword());
            pstmt.setBoolean(5, user.getAdmin());
            pstmt.setBoolean(6, user.getLocked());

            count = pstmt.executeUpdate();
            if (count > 0) {
                logger.info("user = " + user.getLogin() + ". DB inserted");
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
    public boolean update(User user) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final int count;
        try {
            con = ds.getConnection();
            logger.info("connection ==> " + con);
            pstmt = con.prepareStatement(SQL_UPDATE_USER);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getLastname());
            pstmt.setString(4, user.getPassword());
            pstmt.setBoolean(5, user.getAdmin());
            pstmt.setBoolean(6, user.getLocked());
            pstmt.setInt(7, user.getId());
            count = pstmt.executeUpdate();
            logger.info("update#Executed");
            if (count > 0) {
                logger.info("user = " + user.getLogin() + ". DB updated");
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
            pstmt = con.prepareStatement(SQL_DELETE_USER);

            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
            logger.info("delete#Executed");
            if (count > 0) {
                logger.info("user id = " + id + ". DB deleted");
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
    public List<User> getAllUser(int id, int currentPage, int recordsPerPage) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int start = currentPage * recordsPerPage - recordsPerPage;

        List<User> users = new ArrayList<>();
        final int count;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ALL_USER);
            pstmt.setInt(1, id);
            pstmt.setInt(2, start);
            pstmt.setInt(3, recordsPerPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(extractUser(rs));
            }
            logger.info("Selected users ==> " + users.size() + " counts.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(con, logger);
            close(pstmt, logger);
            close(rs, logger);
        }
        return users;
    }

    @Override
    public Integer getNumberOfRows() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;
        final int count;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_USER_ROWS_COUNT);

            if (rs.next()) {
                logger.info("Selected user count ==> " + rs.getInt("cnt") + " .");
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

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setLastname(rs.getString("lastname"));
        user.setPassword(rs.getString("password"));
        user.setAdmin(rs.getBoolean("admin"));
        user.setLocked(rs.getBoolean("locked"));
        return user;
    }

}
