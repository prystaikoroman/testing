package Controller.Subject;

import Controller.Command;
import DAO.SubjectDaoImpl;
import model.Subject;
import org.apache.log4j.Logger;
import service.SubjectService;
import service.SubjectServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditSubject implements Command {
    private final static Logger logger = Logger.getLogger(EditSubject.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        SubjectService subjectService = new SubjectServiceImpl();

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        subjectService.update(req, resp);

        return req.getContextPath()+"/jsp/admSubjectMenager.jsp";
    }
}
