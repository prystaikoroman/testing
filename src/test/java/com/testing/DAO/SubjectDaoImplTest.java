package com.testing.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.testing.Exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;

import com.testing.model.Subject;
import org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper;

import org.junit.jupiter.api.Test;

class SubjectDaoImplTest {

    @Test
    void testFindByName() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
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
        Subject actualFindByNameResult = (new SubjectDaoImpl(dataSourceWrapper)).findByName("Name");
        assertEquals(1, actualFindByNameResult.getId());
        assertEquals("String", actualFindByNameResult.getName());
        verify(dataSourceWrapper).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
        verify(resultSet).next();
        verify(resultSet).getInt((String) any());
        verify(resultSet).getString((String) any());
        verify(resultSet).close();
    }

     @Test
    void testSave() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        SubjectDaoImpl subjectDaoImpl = new SubjectDaoImpl(dataSource);

        Subject subject = new Subject();
        subject.setId(1);
        subject.setName("Name");
        assertTrue(subjectDaoImpl.save(subject));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
    }

       @Test
    void testUpdate() throws DBException, SQLException {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setString(anyInt(), (String) any());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);
        doNothing().when(connection).close();
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(connection);
        SubjectDaoImpl subjectDaoImpl = new SubjectDaoImpl(dataSource);

        Subject subject = new Subject();
        subject.setId(1);
        subject.setName("Name");
        assertTrue(subjectDaoImpl.update(subject));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).setString(anyInt(), (String) any());
        verify(preparedStatement).close();
    }

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
        assertTrue((new SubjectDaoImpl(dataSource)).delete(1));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
    }

   @Test
    void testGetAllSubjects() throws DBException, SQLException {
        ResultSet resultSet = mock(ResultSet.class);
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
        List<Subject> actualAllSubjects = (new SubjectDaoImpl(dataSource)).getAllSubjects(1, 1);
        assertEquals(2, actualAllSubjects.size());
        Subject getResult = actualAllSubjects.get(0);
        assertEquals("String", getResult.getName());
        Subject getResult1 = actualAllSubjects.get(1);
        assertEquals("String", getResult1.getName());
        assertEquals(1, getResult1.getId());
        assertEquals(1, getResult.getId());
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet, atLeast(1)).getString((String) any());
        verify(resultSet).close();
    }

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
        assertEquals(1, (new SubjectDaoImpl(dataSource)).getNumberOfRows().intValue());
        verify(dataSource).getConnection();
        verify(connection).createStatement();
        verify(connection).close();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
        verify(resultSet).next();
        verify(resultSet, atLeast(1)).getInt((String) any());
        verify(resultSet).close();
    }
}

