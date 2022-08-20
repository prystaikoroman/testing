package Controller.Admin.Querie;

import Controller.Command;
import DAO.QuerieDaoImpl;
import DAO.TestDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuerie implements Command {
    private final static Logger logger = Logger.getLogger(DeleteQuerie.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("entered#execute");
        QuerieDaoImpl querieDao = new QuerieDaoImpl();

        querieDao.delete(Integer.parseInt(req.getParameter("querieId")));
        return req.getContextPath()+"/jsp/admQuerieMenager.jsp";
    }
}
