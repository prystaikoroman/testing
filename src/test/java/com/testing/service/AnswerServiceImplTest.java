package com.testing.service;

import com.testing.Exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AnswerServiceImplTest {

    AnswerServiceImpl answerServiceImpl = null;
    HttpServletRequest req = null;
    HttpServletResponse resp = null;


    @Before
    public final void setUp() {

        // Arrange
        answerServiceImpl = mock(AnswerServiceImpl.class);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        when(req.getParameter("answer")).thenReturn("AAPL");
        when(req.getParameter("correct")).thenReturn("on");
        when(req.getParameter("querie_Id")).thenReturn("1");

    }


    @Test
    void testSave() throws DBException {
        answerServiceImpl = mock(AnswerServiceImpl.class);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        // Act
        boolean actualSaveResult = answerServiceImpl.save(req, resp);

        // Assert

    }

    @Test
    public final void testSavePositive() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            answerServiceImpl.save(null, null);
        });

    }

//    @Test
//    void testFindById() {
//        assertNull((new AnswerServiceImpl()).findById(1));
//    }
//
//
//    @Test
//    void testSave() {
//
//
//        AnswerServiceImpl answerServiceImpl = null;
//        HttpServletRequest req = null;
//        HttpServletResponse resp = null;
//
//
//        boolean actualSaveResult = answerServiceImpl.save(req, resp);
//
//        assertFalse(actualSaveResult);
//
//    }
//
//      @Test
//    void testUpdate() {
//         AnswerServiceImpl answerServiceImpl = null;
//        HttpServletRequest req = null;
//        HttpServletResponse resp = null;
//
//
//        boolean actualUpdateResult = answerServiceImpl.update(req, resp);
//
//        assertFalse(actualUpdateResult);
//    }
//
//
//    @Test
//    void testDelete() {
//
//    }

}

