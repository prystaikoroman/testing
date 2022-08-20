package Controller.Subject;

import Controller.Command;
import DAO.SubjectDaoImpl;
import model.Subject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditSubject implements Command {
    private final static Logger logger = Logger.getLogger(EditSubject.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        SubjectDaoImpl subjectDao = new SubjectDaoImpl();
        Subject subject = new Subject();

        subject.setId(Integer.parseInt(req.getParameter("subjectId")));
        subject.setName(req.getParameter("name"));

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        subjectDao.update(subject);

        return req.getContextPath()+"/jsp/admSubjectMenager.jsp";
    }
}
