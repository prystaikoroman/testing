package Controller.Subject;

import Controller.Command;
import DAO.SubjectDaoImpl;

import model.Subject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSubject implements Command {
    private final static Logger logger = Logger.getLogger(AddSubject.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        SubjectDaoImpl subjectDao = new SubjectDaoImpl();
        Subject subject = new Subject();
        subject.setName(req.getParameter("name"));
        subjectDao.save(subject);
        return req.getContextPath()+"/jsp/admSubjectMenager.jsp";

    }
}
