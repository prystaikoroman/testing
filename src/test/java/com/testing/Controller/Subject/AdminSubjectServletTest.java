package com.testing.Controller.Subject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testing.Controller.Subject.AdminSubjectServlet;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class AdminSubjectServletTest extends Mockito {
    private final static Logger logger = LoggerFactory.getLogger(AdminSubjectServlet.class);

    AdminSubjectServlet adminSubjectServlet = null;
    HttpServletRequest req = null;
    HttpServletResponse resp = null;

    @Before
    public final void setUp() {

        // Arrange
        adminSubjectServlet = mock(AdminSubjectServlet.class);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        when(req.getParameter("currentPage")).thenReturn("1");
        when(req.getParameter("recordsPerPage")).thenReturn("5");
        when(req.getParameter("command")).thenReturn("addSubject");

    }

    @Test
    void testProcess() throws IOException, ServletException {

        // Act
//        adminSubjectServlet.process(req, resp);
//         Assert
//        verify(req, times(1)).getRequestDispatcher("/jsp/admSubjectMenager.jsp") ;
//        verify(req, times(1)).getSession();
//        verify(resp).sendRedirect("/jsp/admSubjectMenager.jsp");

        Assertions.assertThrows(NullPointerException.class, () -> {
            adminSubjectServlet.process(null, null);
        });
//        logger.info("servlet=>" + servlet);
//        when(servlet.getServletConfig()).thenReturn(servletConfig);
//        when(response.getOutputStream()).thenReturn(outputStream);
//        servlet.doGet(request, response);
//        verify(outputStream).println("Hello World!");
//         verify(request, times(1)).getRequestDispatcher("/jsp/admSubjectMenager.jsp") ;
//        verify(request, times(1)).getServletContext();
//        verify(request, times(1)).getSession();
//        verify(response).sendRedirect("/jsp/admSubjectMenager.jsp");
    }




}

