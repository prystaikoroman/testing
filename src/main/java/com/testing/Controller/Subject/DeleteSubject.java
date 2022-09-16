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

public class DeleteSubject implements Command {
    private final static Logger logger = LoggerFactory.getLogger(DeleteSubject.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws DBException {
        logger.info("entered#execute");
        SubjectService subjectService = new SubjectServiceImpl();


        subjectService.delete(req, resp);
        return req.getContextPath()+"/jsp/admSubjectMenager.jsp";
    }
}
