package Controller.Subject;

import Controller.Command;
import DAO.SubjectDaoImpl;
import org.apache.log4j.Logger;
import service.SubjectService;
import service.SubjectServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Exception.DBException;

public class DeleteSubject implements Command {
    private final static Logger logger = Logger.getLogger(DeleteSubject.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        SubjectService subjectService = new SubjectServiceImpl();


        subjectService.delete(req, resp);
        return req.getContextPath()+"/jsp/admSubjectMenager.jsp";
    }
}
