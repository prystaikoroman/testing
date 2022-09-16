package com.testing.Controller.Subject;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testing.Exception.DBException;

class DeleteSubjectTest  extends Mockito {
    private final static Logger logger = LoggerFactory.getLogger(DeleteSubjectTest.class);
    ServletOutputStream outputStream =  null;
    ServletConfig servletConfig =  null;
    DeleteSubject servlet =  null;

    HttpServletRequest request = null;
    HttpServletResponse response = null;
    ServletContext servletContext = null;;

    @Before
    public final void setUp() {
         outputStream =  mock(ServletOutputStream.class);
         servletConfig =  mock(ServletConfig.class);
         servlet =  mock(DeleteSubject.class);

         request = mock(HttpServletRequest.class);
         response = mock(HttpServletResponse.class);
         servletContext = mock(ServletContext.class);;

    }

    @Test
    void execute() throws DBException {

        logger.info("servlet=>" + servlet);
        // Act
        String actualExecuteResult = servlet.execute(request, response, servletContext);
        // Assert
//        verify(request, times(1)).getRequestDispatcher("/jsp/admSubjectMenager.jsp") ;
//        verify(req, times(1)).getSession();
//        verify(re sp).sendRedirect("/jsp/admSubjectMenager.jsp");

        Assertions.assertThrows(NullPointerException.class, () -> {
//            servlet.execute(null, null, null);
        });

//        when(deleteSubject.getServletConfig()).thenReturn(servletConfig);
//        when(response.getOutputStream()).thenReturn(outputStream);
//        servlet.process(request, response);
//        verify(outputStream).println("Hello World!");
//         verify(request, times(1)).getRequestDispatcher("/jsp/admSubjectMenager.jsp") ;
//        verify(request, times(1)).getServletContext();
//        verify(request, times(1)).getSession();
//        verify(response).sendRedirect("/jsp/admSubjectMenager.jsp");

    }
}