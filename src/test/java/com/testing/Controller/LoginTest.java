package com.testing.Controller;

import com.testing.Exception.AuthException;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testing.Controller.Login;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginTest {

    @Test
    void testExecute() throws IOException {
        Login login = new Login();

        ServletContext servletContext = mock(ServletContext.class);
        when(servletContext.getServletContextName()).thenReturn("mock");
        ServletConfig servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);

        when(req.getParameter("login")).thenReturn("user");
        when(req.getParameter("password")).thenReturn("user");

//        DSInstance ds = mock(DSInstance.class);
//        when(DSInstance.getInstance().getDs()).thenReturn(ds.getDs());
//
//        String actualExecuteResult = login.execute(req, resp, servletContext);

    }

    @Test
    void testUserRole() throws AuthException {

    }

}

