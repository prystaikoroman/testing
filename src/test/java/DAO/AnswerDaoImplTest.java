package DAO;

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

import Exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;
import model.Answer;

import model.User_Test_Answer;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AnswerDaoImplTest {

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
    void testSave() throws DBException, SQLException {
        AnswerDaoImpl answerDaoImpl = new AnswerDaoImpl(ds);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Answer answer = new Answer();
        answer.setAnswer("Answer");
        answer.setCorrect(true);
        answer.setId(1);
        answer.setQuerie_id(1);
        answer.setUserChecking(true);
        assertTrue(answerDaoImpl.save(answer));
    }

    @Test
    void testUpdate() throws DBException, SQLException {
        AnswerDaoImpl answerDaoImpl = new AnswerDaoImpl(ds);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        Answer answer = new Answer();
        answer.setAnswer("Answer");
        answer.setCorrect(true);
        answer.setId(2);
        answer.setQuerie_id(1);
        answer.setUserChecking(true);
        assertTrue(answerDaoImpl.update(answer));
    }

    @Test
    void testDelete() throws DBException {
        (new AnswerDaoImpl(ds)).delete(1);
    }

    @Test
    void testInsertUser_Test_Answer() throws DBException, SQLException {
        AnswerDaoImpl answerDaoImpl = new AnswerDaoImpl(ds);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(preparedStatement).setBoolean(anyInt(), anyBoolean());
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        User_Test_Answer user_Test_Answer = new User_Test_Answer();
        user_Test_Answer.setAnswer_id(1);
        user_Test_Answer.setCorrect(true);
        user_Test_Answer.setSelected(true);
        user_Test_Answer.setUser_test_test_id(1);
        user_Test_Answer.setUser_test_user_id(1);
        assertTrue(answerDaoImpl.insertUser_Test_Answer(connection, user_Test_Answer));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement, atLeast(1)).setBoolean(anyInt(), anyBoolean());
        verify(preparedStatement, atLeast(1)).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
    }

    @Test
    void testInsertUser_Test_Answer2() throws DBException, SQLException {
        AnswerDaoImpl answerDaoImpl = new AnswerDaoImpl(ds);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(preparedStatement).setBoolean(anyInt(), anyBoolean());
        doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
        doThrow(new SQLException()).when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        doNothing().when(connection).rollback();
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        User_Test_Answer user_Test_Answer = new User_Test_Answer();
        user_Test_Answer.setAnswer_id(1);
        user_Test_Answer.setCorrect(true);
        user_Test_Answer.setSelected(true);
        user_Test_Answer.setUser_test_test_id(1);
        user_Test_Answer.setUser_test_user_id(1);
        assertThrows(DBException.class, () -> answerDaoImpl.insertUser_Test_Answer(connection, user_Test_Answer));
        verify(connection).prepareStatement((String) any());
        verify(connection).rollback();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
    }

    @Test
    void testInsertUser_Test_Answer3() throws DBException, SQLException {
        AnswerDaoImpl answerDaoImpl = new AnswerDaoImpl(ds);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
        doThrow(new SQLException()).when(preparedStatement).setBoolean(anyInt(), anyBoolean());
        doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());
        doThrow(new SQLException()).when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        doThrow(new SQLException()).when(connection).rollback();
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        User_Test_Answer user_Test_Answer = new User_Test_Answer();
        user_Test_Answer.setAnswer_id(1);
        user_Test_Answer.setCorrect(true);
        user_Test_Answer.setSelected(true);
        user_Test_Answer.setUser_test_test_id(1);
        user_Test_Answer.setUser_test_user_id(1);
        assertThrows(DBException.class, () -> answerDaoImpl.insertUser_Test_Answer(connection, user_Test_Answer));
        verify(connection).prepareStatement((String) any());
        verify(connection).rollback();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(preparedStatement).close();
    }

    @Test
    void testGetAllAnswers() throws DBException {
        (new AnswerDaoImpl(ds)).getAllAnswers(1, 1, 1);
    }

    @Test

    void testGetAllAnswers_UserSubmition() throws DBException {
        (new AnswerDaoImpl(ds)).getAllAnswers_UserSubmition(1, 1, 1);
    }

    @Test
    void testGetNumberOfRows() throws DBException {
        (new AnswerDaoImpl(ds)).getNumberOfRows();
    }

}

