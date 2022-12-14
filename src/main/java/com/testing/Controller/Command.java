package com.testing.Controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.testing.Exception.DBException;
public interface Command {
    String execute (HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws IOException, DBException;
}
