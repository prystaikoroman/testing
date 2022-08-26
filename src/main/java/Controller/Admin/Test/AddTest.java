package Controller.Admin.Test;

import Controller.Command;
import model.Test;
import org.apache.log4j.Logger;
import service.TestService;
import service.TestServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTest implements Command {
    private final static Logger logger = Logger.getLogger(AddTest.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) {
        logger.info("entered#execute");
        TestService testService = new TestServiceImpl();
        testService.save(req, resp);
        return req.getContextPath()+"/jsp/admTestMenager.jsp";

    }
}
