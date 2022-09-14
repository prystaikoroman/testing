package DAO;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import DTO.LoginDto;
import Exception.AuthException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginDaoTest {

    MysqlDataSource ds;
    @BeforeEach
    public void setUp() {
        ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("testing_test");
        ds.setUser("user");
        ds.setPassword("user");
    }

    @Test
    void testAuthenticateUser() throws AuthException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getBoolean((String) any())).thenReturn(true);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        doNothing().when(statement).close();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        LoginDao loginDao = new LoginDao(dataSource);

        LoginDto loginDto = new LoginDto();
        loginDto.setLogin("Login");
        loginDto.setPassword("Login");
        assertThrows(AuthException.class, () -> loginDao.authenticateUser(loginDto));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
        verify(resultSet, atLeast(1)).getBoolean((String) any());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet).close();
    }

    @Test
    void testAuthenticateUser2() throws AuthException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getBoolean((String) any())).thenThrow(new SQLException());
        when(resultSet.getString((String) any())).thenThrow(new SQLException());
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        doNothing().when(statement).close();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        LoginDao loginDao = new LoginDao(dataSource);

        LoginDto loginDto = new LoginDto();
        loginDto.setLogin("Login");
        loginDto.setPassword("Login");
        assertThrows(AuthException.class, () -> loginDao.authenticateUser(loginDto));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
        verify(resultSet).next();
        verify(resultSet).getString((String) any());
        verify(resultSet).close();
    }

    @Test
    void testAuthenticateUser3() throws AuthException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(resultSet).close();
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        doNothing().when(statement).close();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        LoginDao loginDao = new LoginDao(dataSource);

        LoginDto loginDto = new LoginDto();
        loginDto.setLogin("login");
        loginDto.setPassword("Login");
        assertThrows(AuthException.class, () -> loginDao.authenticateUser(loginDto));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
        verify(resultSet).next();
        verify(resultSet).close();
    }

    @Test
    void testAuthenticateUser4() throws AuthException, SQLException {
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenThrow(new SQLException());
        doThrow(new SQLException()).when(statement).close();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        LoginDao loginDao = new LoginDao(dataSource);

        LoginDto loginDto = new LoginDto();
        loginDto.setLogin("Login");
        loginDto.setPassword("Login");
        assertThrows(AuthException.class, () -> loginDao.authenticateUser(loginDto));
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
    }
}

