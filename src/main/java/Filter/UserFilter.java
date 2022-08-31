package Filter;

import Controller.Admin.User.AdminUserServlet;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/adminUser"} //, initParams = { @WebInitParam(name = "active", value = "xxx") }
)

public class UserFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(UserFilter.class);
    FilterConfig config;

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        logger.info("entered#doFilter");
        //        if (config.getInitParameter("active").equalsIgnoreCase("true")) {

        System.out.println("Filter sets attribute.");
        String commandName = req.getParameter("command");
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession();//create session

        if (!commandName.equals("registerUser")) {

            config.getServletContext().setAttribute("login", session.getAttribute("Admin"));
            config.getServletContext().setAttribute("Admin", session.getAttribute("Admin"));

        } else {
            config.getServletContext().setAttribute("login", req.getParameter("login"));
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

