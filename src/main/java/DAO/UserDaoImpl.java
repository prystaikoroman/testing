package DAO;

import model.User;
import org.apache.log4j.Logger;
import util.DSInstance;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements userDao {
    private static final String SQL_SELECT_ALL_USER = "SELECT * USER WHERE id != 0";
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
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> getAllUser() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL_USER);
            while (rs.next()) {
                users.add(extractUser(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(stmt);
            close(rs);
        }
        return users;
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

    private void close(AutoCloseable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
