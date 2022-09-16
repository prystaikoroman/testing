package com.testing.Controller.Admin.Querie;

import com.testing.Controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.service.QuerieService;
import com.testing.service.QuerieServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.testing.Exception.DBException;

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
