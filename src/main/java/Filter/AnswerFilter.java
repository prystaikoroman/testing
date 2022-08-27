package Filter;

import Controller.Admin.Answer.AdminAnswerCommandContainer;
import Controller.Command;
import org.apache.log4j.Logger;
import service.AnswerService;
import service.AnswerServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/adminAnswer"} //, initParams = { @WebInitParam(name = "active", value = "xxx") }
)

public class AnswerFilter implements Filter {
    private final static Logger logger = Logger.getLogger(AnswerFilter.class);
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

        config.getServletContext().setAttribute("querie_Id", req.getParameter("querie_Id"));
        config.getServletContext().setAttribute("test_Id", req.getParameter("test_Id"));
        config.getServletContext().setAttribute("subject_Id", req.getParameter("subject_Id"));

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }
}

