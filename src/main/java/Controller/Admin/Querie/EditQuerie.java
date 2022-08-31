package Controller.Admin.Querie;

import Controller.Command;
import DAO.QuerieDaoImpl;
import DAO.TestDaoImpl;
import model.Querie;
import model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.QuerieService;
import service.QuerieServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Exception.DBException;

public class EditQuerie implements Command {
    private final static Logger logger = LoggerFactory.getLogger(EditQuerie.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        QuerieService querieService = new QuerieServiceImpl();

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        querieService.update( req, resp);

        return req.getContextPath()+"/jsp/admQuerieMenager.jsp";
    }
}
