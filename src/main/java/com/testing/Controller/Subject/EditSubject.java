package com.testing.Controller.Subject;

import com.testing.Controller.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.service.SubjectService;
import com.testing.service.SubjectServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.testing.Exception.DBException;

public class EditSubject implements Command {
    private final static Logger logger = LoggerFactory.getLogger(EditSubject.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        SubjectService subjectService = new SubjectServiceImpl();

//        logger.info("locked ==> " + req.getParameter("locked").equals("on"));
        subjectService.update(req, resp);

        return req.getContextPath()+"/jsp/admSubjectMenager.jsp";
    }
}
