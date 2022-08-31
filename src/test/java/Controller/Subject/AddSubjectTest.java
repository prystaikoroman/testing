package Controller.Subject;

import Exception.DBException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AddSubjectTest {
    private final static Logger logger = LoggerFactory.getLogger(AdminSubjectServlet.class);

    @Test
    @Disabled("TODO: Complete this test")
    void testExecute() throws DBException {
        ServletOutputStream outputStream =  mock(ServletOutputStream.class);
        ServletConfig servletConfig =  mock(ServletConfig.class);
        AdminSubjectServlet servlet =  mock(AdminSubjectServlet.class);
        AddSubject addSubject =  mock(AddSubject.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext servletContext = mock(ServletContext.class);;

        logger.info("servlet=>" + servlet);
        // Act
        String actualExecuteResult = addSubject.execute(request, response, servletContext);
        // Assert
//        verify(req, times(1)).getRequestDispatcher("/jsp/admSubjectMenager.jsp") ;
//        verify(req, times(1)).getSession();
//        verify(resp).sendRedirect("/jsp/admSubjectMenager.jsp");


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