package Controller.Admin.Querie;

import Controller.Command;
import service.TestService;
import service.TestServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserQueriesPass implements Command {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws IOException {
        TestService testService = new TestServiceImpl();
        testService.insertUser_Test( req,  resp,  servletContext);
        return req.getContextPath()+"/jsp/admQuerieMenager.jsp";
    }
}
