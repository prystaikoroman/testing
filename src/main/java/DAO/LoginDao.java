package DAO;

import DTO.LoginDto;
import org.apache.log4j.Logger;
import util.DSInstance;

import javax.sql.DataSource;
import java.sql.*;

import static util.EmptyResources.close;

public class LoginDao {
    private static String SQL_SELECT_USER = "select login,password,admin from user";
    private static final Logger logger = Logger.getLogger(LoginDao.class);

    public String authenticateUser(LoginDto loginDto) {
        String login = loginDto.getLogin();
        String password = loginDto.getPassword();

        DataSource ds = DSInstance.getInstance().getDs();

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String loginDB = "";
        String passwordDB = "";
        boolean adminDB = false;
        String role = "";
        try {
            con = ds.getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_USER);

            while (resultSet.next()) {
                loginDB = resultSet.getString("login");
                passwordDB = resultSet.getString("password");
                adminDB = resultSet.getBoolean("admin");

                if (login.equals(loginDB) && password.equals(passwordDB) && adminDB) {
                    role = "ADMIN_ROLE";
                    logger.info(role);
                    return role;
                } else if (login.equals(loginDB) && password.equals(passwordDB)) {
                    role = "USER_ROLE";
                    logger.info(role);
                    return role;
                }
            }
        } catch (SQLException e) {
            logger.error( e.getMessage());
        }
        finally {
        close(con, logger);
        close(statement, logger);
        close(resultSet, logger);
    }

    role = "Invalid user credentials";
        logger.error( role);
        return role;
    }
}
