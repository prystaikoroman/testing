package Filter.validator;

        import java.io.IOException;
        import javax.servlet.Filter;
        import javax.servlet.FilterChain;
        import javax.servlet.FilterConfig;
        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRequest;
        import javax.servlet.ServletResponse;
        import javax.servlet.annotation.WebFilter;
        import org.apache.commons.validator.routines.EmailValidator;

@WebFilter(filterName = "LoginValidation", urlPatterns = {"/LoginServlet"})
public class LoginValidation implements Filter {

    public LoginValidation() { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        String erpg = "valError.jsp";
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        if (login == null || "".equals(login)
                || password == null || "".equals(password)) {

            request.setAttribute("errMessage", "One or both fields are empty");

            RequestDispatcher rd = request.getRequestDispatcher(erpg);
            rd.include(request, response);

           } else {

            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() { }

    @Override
    public void init(FilterConfig filterConfig) { }

}