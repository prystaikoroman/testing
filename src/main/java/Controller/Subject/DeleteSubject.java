package Controller.Subject;

import Controller.Command;
import DAO.SubjectDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteSubject implements Command {
    private final static Logger logger = Logger.getLogger(DeleteSubject.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        SubjectDaoImpl subjectDao = new SubjectDaoImpl();

        subjectDao.delete(Integer.parseInt(req.getParameter("subjectId")));
        return req.getContextPath()+"/jsp/admSubjectMenager.jsp";
    }
}
