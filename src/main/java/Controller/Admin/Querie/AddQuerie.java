package Controller.Admin.Querie;

import Controller.Command;
import DAO.QuerieDaoImpl;
import DAO.TestDaoImpl;
import model.Querie;
import model.Test;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddQuerie implements Command {
    private final static Logger logger = Logger.getLogger(AddQuerie.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        QuerieDaoImpl querieDao = new QuerieDaoImpl();
        Querie querie = new Querie();
        querie.setQuestion(req.getParameter("question"));
        querie.setTest_id(Integer.parseInt(req.getParameter("test_Id")));
        querieDao.save(querie);
        return req.getContextPath()+"/jsp/admQuerieMenager.jsp";

    }
}
