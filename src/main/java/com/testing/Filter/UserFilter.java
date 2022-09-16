package com.testing.Filter;

import com.testing.DAO.UserDao;
import com.testing.DAO.UserDaoImpl;
import com.testing.util.DSInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@WebFilter(urlPatterns = {"/adminUser"} //, initParams = { @WebInitParam(name = "active", value = "xxx") }
)

public class UserFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(UserFilter.class);
    FilterConfig config;
    private static final DataSource ds = DSInstance.getInstance().getDs();
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        logger.info("entered#doFilter");
        //        if (config.getInitParameter("active").equalsIgnoreCase("true")) {
        String login = req.getParameter("login");
        System.out.println("Filter sets attribute.");
        String commandName = req.getParameter("command");
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession();//create session
        UserDao userDao= new UserDaoImpl(ds);

            if (!commandName.equals("registerUser")) {

                config.getServletContext().setAttribute("login", session.getAttribute("Admin"));
                config.getServletContext().setAttribute("Admin", session.getAttribute("Admin"));
//                session.setAttribute("Admin", login);
//                session.setAttribute("AdminUser", userDao.findByLogin(login));
            } else {
                config.getServletContext().setAttribute("login", req.getParameter("User"));
                config.getServletContext().setAttribute("password", req.getParameter("password"));

            }



//            config.getServletContext().setAttribute("Attribute", "Value");
//        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }
}

