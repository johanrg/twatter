package filter;

import controller.LoginController;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Johan Gustafsson
 * @since 2016-08-15.
 */
@WebFilter(urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {
    @Inject
    LoginController user;

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            String reqURI = reqt.getRequestURI();

            if (reqURI.contains("/admin/")) {
                if (!user.isAdmin() || !user.isLoggedIn()) {
                    resp.sendRedirect(reqt.getContextPath() + "/faces/forum.xhtml");
                }
            } else if (reqURI.contains("/newpost.xhtml") && !user.isLoggedIn()) {
                resp.sendRedirect(reqt.getContextPath() + "/faces/forum.xhtml");
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
    }
}
