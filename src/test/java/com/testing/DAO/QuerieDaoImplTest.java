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

import com.testing.model.Querie;

import org.junit.jupiter.api.Test;

class QuerieDaoImplTest {


    @Test
    void testSave() throws DBException, SQLException {
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
        QuerieDaoImpl querieDaoImpl = new QuerieDaoImpl(dataSource);

        Querie querie = new Querie();
        querie.setId(1);
        querie.setQuestion("Question");
        querie.setTest_id(1);
        assertTrue(querieDaoImpl.save(querie));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setInt(anyInt(), anyInt());
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
        QuerieDaoImpl querieDaoImpl = new QuerieDaoImpl(dataSource);

        Querie querie = new Querie();
        querie.setId(1);
        querie.setQuestion("Question");
        querie.setTest_id(1);
        assertTrue(querieDaoImpl.update(querie));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).setString(anyInt(), (String) any());
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
        assertTrue((new QuerieDaoImpl(dataSource)).delete(1));
        verify(dataSource).getConnection();
        verify(connection).prepareStatement((String) any());
        verify(connection).close();
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
    }

    @Test
    void testGetAllQueries() throws DBException, SQLException {
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
        List<Querie> actualAllQueries = (new QuerieDaoImpl(dataSource)).getAllQueries(1, 1, 1);
        assertEquals(2, actualAllQueries.size());
        Querie getResult = actualAllQueries.get(0);
        assertEquals(1, getResult.getTest_id());
        Querie getResult1 = actualAllQueries.get(1);
        assertEquals(1, getResult1.getTest_id());
        assertEquals("String", getResult1.getQuestion());
        assertEquals(1, getResult1.getId());
        assertEquals("String", getResult.getQuestion());
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
        assertEquals(1, (new QuerieDaoImpl(dataSource)).getNumberOfRows().intValue());
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

