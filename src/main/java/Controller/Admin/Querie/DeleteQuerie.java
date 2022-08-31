package Controller.Admin.Querie;

import Controller.Command;
import DAO.QuerieDaoImpl;
import DAO.TestDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.QuerieService;
import service.QuerieServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Exception.DBException;

public class DeleteQuerie implements Command {
    private final static Logger logger = LoggerFactory.getLogger(DeleteQuerie.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        QuerieService querieService = new QuerieServiceImpl();

        querieService.delete(Integer.parseInt(req.getParameter("querieId")));
        return req.getContextPath()+"/jsp/admQuerieMenager.jsp";
    }
}
