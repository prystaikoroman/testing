package Controller.Admin.Test;

import Controller.Command;
import DAO.SubjectDaoImpl;
import DAO.TestDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TestService;
import service.TestServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Exception.DBException;

public class DeleteTest implements Command {
    private final static Logger logger = LoggerFactory.getLogger(DeleteTest.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        TestService testService = new TestServiceImpl();

        testService.delete(req, resp);
        return req.getContextPath()+"/jsp/admTestMenager.jsp";
    }
}
