package com.testing.Controller;

import com.testing.Controller.LoginServlet;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServletTest {

    @Before
    public void setUp() {
     }
    
    @Test
    void process() throws IOException, ServletException {
        LoginServlet servlet = new LoginServlet();
//        ServletConfig servletConfig =  servlet.getServletConfig();
        ServletContext servletContext = mock(ServletContext.class);
        when(servletContext.getServletContextName()).thenReturn("mock");
        ServletConfig servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletContext()).thenReturn(servletContext);

//        ServletContext servletContext = servletConfig.getServletContext();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);


        String strCommandName = "login";

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("command")).thenReturn("");
//        when(servlet.getServletContext()).thenReturn(servlet.getServletConfig().getServletContext());
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        verify(response).sendRedirect(captor.capture());

        assertEquals("null/jsp/login.jsp", captor.getValue());
    }

    }

