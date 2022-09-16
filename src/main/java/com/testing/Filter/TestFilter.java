package com.testing.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.testing.service.AnswerService;
import com.testing.service.AnswerServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/adminTest"} //, initParams = { @WebInitParam(name = "active", value = "xxx") }
)

public class TestFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(TestFilter.class);
    FilterConfig config;

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        logger.info("entered#doFilter");
        //        if (config.getInitParameter("active").equalsIgnoreCase("true")) {

        System.out.println("Filter sets attribute.");

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession();//create session
        AnswerService answerService= new AnswerServiceImpl();


        if (session.getAttribute("Admin") !=null) {
            //        session.setAttribute("Admin", session.getAttribute("login"));
            config.getServletContext().setAttribute("login", session.getAttribute("Admin"));
            config.getServletContext().setAttribute("Admin", session.getAttribute("Admin"));
        } else {
            config.getServletContext().setAttribute("login", session.getAttribute("User"));
            config.getServletContext().setAttribute("Admin", session.getAttribute("User"));
        }

        config.getServletContext().setAttribute("subject_Id", req.getParameter("subject_Id"));

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }
}

