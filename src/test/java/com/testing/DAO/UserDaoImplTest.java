package com.testing.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.testing.Exception.DBException;
import com.testing.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper;

import org.junit.jupiter.api.Test;

class UserDaoImplTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserDaoImpl#UserDaoImpl(DataSource)}
     *   <li>{@link UserDaoImpl#findByEmail(String)}
     *   <li>{@link UserDaoImpl#findById(int)}
     * </ul>
     */
    @Test
    void testConstructor() {
        UserDaoImpl actualUserDaoImpl = new UserDaoImpl(mock(DataSource.class));
        User actualFindByEmailResult = actualUserDaoImpl.findByEmail("jane.doe@example.org");
        assertNull(actualFindByEmailResult);
        assertNull(actualUserDaoImpl.findById(1));
    }

    /**
     * Method under test: {@link UserDaoImpl#findByLogin(String)}
     */
    @Test
    void testFindByLogin() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getBoolean((String) any())).thenReturn(true);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSourceWrapper dataSourceWrapper = mock(DataSourceWrapper.class);
        when(dataSourceWrapper.getConnection()).thenReturn(connection);
        User actualFindByLoginResult = (new UserDaoImpl(dataSourceWrapper)).findByLogin("Login");
        assertTrue(actualFindByLoginResult.getAdmin());
        assertEquals("String", actualFindByLoginResult.getPassword());
        assertEquals("String", actualFindByLoginResult.getLogin());
        assertTrue(actualFindByLoginResult.getLocked());
        assertEquals("String", actualFindByLoginResult.getLastname());
        assertEquals(1, actualFindByLoginResult.getId());
        assertEquals("String", actualFindByLoginResult.getEmail());
        verify(dataSourceWrapper).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
        verify(resultSet, atLeast(1)).getBoolean((String) any());
        verify(resultSet).next();
        verify(resultSet).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#findByLogin(String)}
     */
    @Test
    void testFindByLogin2() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getBoolean((String) any())).thenThrow(new SQLException());
        when(resultSet.getInt((String) any())).thenThrow(new SQLException());
        when(resultSet.getString((String) any())).thenThrow(new SQLException());
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSourceWrapper dataSourceWrapper = mock(DataSourceWrapper.class);
        when(dataSourceWrapper.getConnection()).thenReturn(connection);
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSourceWrapper)).findByLogin("Login"));
        verify(dataSourceWrapper).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
        verify(resultSet).next();
        verify(resultSet).getInt((String) any());
        verify(resultSet).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#findByLogin(String)}
     */
    @Test
    void testFindByLogin3() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSourceWrapper dataSourceWrapper = mock(DataSourceWrapper.class);
        when(dataSourceWrapper.getConnection()).thenReturn(connection);
        assertThrows(DBException.class,
                () -> (new UserDaoImpl(dataSourceWrapper)).findByLogin("SELECT * FROM USER WHERE login = ? "));
        verify(dataSourceWrapper).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
        verify(resultSet).next();
        verify(resultSet).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#save(User)}
     */
    @Test
    void testSave() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setBoolean(anyInt(), anyBoolean());
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);

        User user = new User();
        user.setAdmin(true);
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setLastname("Doe");
        user.setLocked(true);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        assertTrue(userDaoImpl.save(user));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement, atLeast(1)).setBoolean(anyInt(), anyBoolean());
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#save(User)}
     */
    @Test
    void testSave2() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(preparedStatement).setBoolean(anyInt(), anyBoolean());
        doThrow(new SQLException()).when(preparedStatement).setString(anyInt(), (String) any());
        doThrow(new SQLException()).when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);

        User user = new User();
        user.setAdmin(true);
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setLastname("Doe");
        user.setLocked(true);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        assertThrows(DBException.class, () -> userDaoImpl.save(user));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#update(User)}
     */
    @Test
    void testUpdate() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setBoolean(anyInt(), anyBoolean());
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);

        User user = new User();
        user.setAdmin(true);
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setLastname("Doe");
        user.setLocked(true);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        assertTrue(userDaoImpl.update(user));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement, atLeast(1)).setBoolean(anyInt(), anyBoolean());
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#update(User)}
     */
    @Test
    void testUpdate2() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(preparedStatement).setBoolean(anyInt(), anyBoolean());
        doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
        doThrow(new SQLException()).when(preparedStatement).setString(anyInt(), (String) any());
        doThrow(new SQLException()).when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);

        User user = new User();
        user.setAdmin(true);
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setLastname("Doe");
        user.setLocked(true);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        assertThrows(DBException.class, () -> userDaoImpl.update(user));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#delete(int)}
     */
    @Test
    void testDelete() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        assertTrue((new UserDaoImpl(dataSource)).delete(1));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#delete(int)}
     */
    @Test
    void testDelete2() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
        doThrow(new SQLException()).when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSource)).delete(1));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getAllUser(int, int, int)}
     */
    @Test
    void testGetAllUser() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getBoolean((String) any())).thenReturn(true);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        assertEquals(2, (new UserDaoImpl(dataSource)).getAllUser(1, 1, 1).size());
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
        verify(resultSet, atLeast(1)).getBoolean((String) any());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getAllUser(int, int, int)}
     */
    @Test
    void testGetAllUser2() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getBoolean((String) any())).thenThrow(new SQLException());
        when(resultSet.getInt((String) any())).thenThrow(new SQLException());
        when(resultSet.getString((String) any())).thenThrow(new SQLException());
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSource)).getAllUser(1, 1, 1));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
        verify(resultSet).next();
        verify(resultSet).getInt((String) any());
        verify(resultSet).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getAllUser(int, int, int)}
     */
    @Test
    void testGetAllUser3() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSource)).getAllUser(123, 1, 1));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
        verify(resultSet).next();
        verify(resultSet).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getAllUser(int, int, int)}
     */
    @Test
    void testGetAllUser4() throws DBException, SQLException {
        new SQLException();
        new SQLException();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
        doThrow(new SQLException()).when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSource)).getAllUser(1, 1, 1));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getNumberOfRows()}
     */
    @Test
    void testGetNumberOfRows() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenReturn(1);
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
        assertEquals(1, (new UserDaoImpl(dataSource)).getNumberOfRows().intValue());
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
        verify(resultSet).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getNumberOfRows()}
     */
    @Test
    void testGetNumberOfRows2() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt((String) any())).thenThrow(new SQLException());
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
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSource)).getNumberOfRows());
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
        verify(resultSet).next();
        verify(resultSet).getInt((String) any());
        verify(resultSet).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getNumberOfRows()}
     */
    @Test
    void testGetNumberOfRows3() throws DBException, SQLException {
        new SQLException();
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
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSource)).getNumberOfRows());
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
        verify(resultSet).next();
        verify(resultSet).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getNumberOfRows()}
     */
    @Test
    void testGetNumberOfRows4() throws DBException, SQLException {
        new SQLException();
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenThrow(new SQLException());
        doThrow(new SQLException()).when(statement).close();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSource)).getNumberOfRows());
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getNumberOfRows()}
     */
    @Test
    void testGetNumberOfRows5() throws DBException, SQLException {
        new SQLException();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSource)).getNumberOfRows());
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
    }

    /**
     * Method under test: {@link UserDaoImpl#getNumberOfRows()}
     */
    @Test
    void testGetNumberOfRows6() throws DBException, SQLException {
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenThrow(new SQLException());
        assertThrows(DBException.class, () -> (new UserDaoImpl(dataSource)).getNumberOfRows());
        verify(dataSource).getConnection();
    }
}

