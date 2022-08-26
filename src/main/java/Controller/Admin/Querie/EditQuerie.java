package Controller.Admin.Querie;

import Controller.Command;
import DAO.QuerieDaoImpl;
import DAO.TestDaoImpl;
import model.Querie;
import model.Test;
import org.apache.log4j.Logger;
import service.QuerieService;
import service.QuerieServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditQuerie implements Command {
    private final static Logger logger = Logger.getLogger(EditQuerie.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) {
        logger.info("entered#execute");
        QuerieService querieService = new QuerieServiceImpl();

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        querieService.update( req, resp);

        return req.getContextPath()+"/jsp/admQuerieMenager.jsp";
    }
}
