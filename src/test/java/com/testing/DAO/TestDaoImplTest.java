package com.testing.DAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyShort;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.testing.Exception.DBException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.sql.DataSource;

import org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDaoImplTest {
    MysqlDataSource ds;
    @BeforeEach
    public void setUp() {
        ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("testing_test");
        ds.setUser("user");
        ds.setPassword("user");
//        UserRepository userRepository = new UserRepository(ds);
    }
    @Test
    void testConstructor() throws DBException {
        TestDaoImpl actualTestDaoImpl = new TestDaoImpl(mock(DataSource.class));
        com.testing.model.Test actualFindByIdResult = actualTestDaoImpl.findById(1);
        com.testing.model.Test actualFindByTaskResult = actualTestDaoImpl.findByTask("Task");
        assertNull(actualFindByIdResult);
        assertNull(actualFindByTaskResult);
        assertNull(actualTestDaoImpl.findUser_TestById(1, 1));
    }

    @Test
    void testUser_Tests_Finished_Upd() throws DBException, SQLException {
        CallableStatement callableStatement = mock(CallableStatement.class);
        when(callableStatement.execute()).thenReturn(true);
        doNothing().when(callableStatement).setInt(anyInt(), anyInt());
        doNothing().when(callableStatement).close();

        assertFalse((new TestDaoImpl(ds)).User_Tests_Finished_Upd(1));
    }

    @Test
    void testUser_Tests_Result() throws DBException, SQLException {
        CallableStatement callableStatement = mock(CallableStatement.class);
        when(callableStatement.execute()).thenReturn(true);
        doNothing().when(callableStatement).setInt(anyInt(), anyInt());
        doNothing().when(callableStatement).close();

        assertFalse((new TestDaoImpl(ds)).User_Tests_Result(ds.getConnection(), 1));
    }


    @Test
    void testFindByName() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getBoolean((String) any())).thenReturn(true);
        when(resultSet.getDouble((String) any())).thenReturn(10.0d);
        when(resultSet.findColumn((String) any())).thenReturn(1);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.getDate((String) any())).thenReturn(mock(Date.class));
        when(resultSet.getShort((String) any())).thenReturn((short) 1);
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
        com.testing.model.Test actualFindByNameResult = (new TestDaoImpl(dataSourceWrapper)).findByName("Name");
        assertEquals(1, actualFindByNameResult.getDifficulty());
        assertTrue(actualFindByNameResult.isFinished());
        assertEquals("String", actualFindByNameResult.getTask());
        assertEquals(1, actualFindByNameResult.getSubject_id());
        assertEquals(10.0d, actualFindByNameResult.getPercent_result());
        assertEquals((short) 1, actualFindByNameResult.getPassingTimeMin());
        assertEquals("String", actualFindByNameResult.getName());
        assertEquals(1, actualFindByNameResult.getId());
        verify(dataSourceWrapper).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
        verify(resultSet).getBoolean((String) any());
        verify(resultSet).next();
        verify(resultSet).getDouble((String) any());
        verify(resultSet).findColumn((String) any());
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet).getDate((String) any());
        verify(resultSet).getShort((String) any());
        verify(resultSet).close();
    }

    @Test
    void testSave() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setShort(anyInt(), anyShort());
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        TestDaoImpl testDaoImpl = new TestDaoImpl(dataSource);

        com.testing.model.Test test = new com.testing.model.Test();
        test.setDifficulty(1);
        test.setFinished(true);
        test.setId(1);
        test.setName("Name");
        test.setPassingTimeMin((short) 1);
        test.setPercent_result(10.0d);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        test.setStarted(java.util.Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        test.setSubject_id(1);
        test.setTask("Task");
        assertTrue(testDaoImpl.save(test));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).setShort(anyInt(), anyShort());
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
    }

      @Test
    void testUpdate() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setShort(anyInt(), anyShort());
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        TestDaoImpl testDaoImpl = new TestDaoImpl(dataSource);

        com.testing.model.Test test = new com.testing.model.Test();
        test.setDifficulty(1);
        test.setFinished(true);
        test.setId(1);
        test.setName("Name");
        test.setPassingTimeMin((short) 1);
        test.setPercent_result(10.0d);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        test.setStarted(java.util.Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        test.setSubject_id(1);
        test.setTask("Task");
        assertTrue(testDaoImpl.update(test));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).setShort(anyInt(), anyShort());
        verify(preparedStatement, atLeast(1)).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
    }


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
        assertTrue((new TestDaoImpl(dataSource)).delete(1));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
    }


    @Test
    void testInsertUser_Test() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setTimestamp(anyInt(), (Timestamp) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSourceWrapper dataSourceWrapper = mock(DataSourceWrapper.class);
        when(dataSourceWrapper.getConnection()).thenReturn(connection);
        assertTrue((new TestDaoImpl(dataSourceWrapper)).insertUser_Test(123, 123));
        verify(dataSourceWrapper).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).setTimestamp(anyInt(), (Timestamp) any());
        verify(preparedStatement).close();
    }


    @Test
    void testGetAllTests() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getBoolean((String) any())).thenReturn(true);
        when(resultSet.getDouble((String) any())).thenReturn(10.0d);
        when(resultSet.findColumn((String) any())).thenReturn(1);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.getDate((String) any())).thenReturn(mock(Date.class));
        when(resultSet.getShort((String) any())).thenReturn((short) 1);
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
        assertEquals(2, (new TestDaoImpl(dataSource)).getAllTests(1, 1, 1).size());
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
        verify(resultSet, atLeast(1)).getBoolean((String) any());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getDouble((String) any());
        verify(resultSet, atLeast(1)).findColumn((String) any());
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet, atLeast(1)).getDate((String) any());
        verify(resultSet, atLeast(1)).getShort((String) any());
        verify(resultSet).close();
    }

    @Test
    void testGetAllUserTests() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getBoolean((String) any())).thenReturn(true);
        when(resultSet.getDouble((String) any())).thenReturn(10.0d);
        when(resultSet.findColumn((String) any())).thenReturn(1);
        when(resultSet.getInt((String) any())).thenReturn(1);
        when(resultSet.getString((String) any())).thenReturn("String");
        when(resultSet.getDate((String) any())).thenReturn(mock(Date.class));
        when(resultSet.getShort((String) any())).thenReturn((short) 1);
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
        assertEquals(2, (new TestDaoImpl(dataSource)).getAllUserTests(mock(Connection.class), 1, 1, 1, 1).size());
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
        verify(resultSet, atLeast(1)).getBoolean((String) any());
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getDouble((String) any());
        verify(resultSet, atLeast(1)).findColumn((String) any());
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet, atLeast(1)).getDate((String) any());
        verify(resultSet, atLeast(1)).getShort((String) any());
        verify(resultSet).close();
    }

    @Test
    void testGetNumberOfRows() throws DBException, SQLException {
        assertEquals(0, (new TestDaoImpl(ds)).getNumberOfRows().intValue());
    }


}

