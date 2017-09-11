package cn.com.weiwang.cms.backend.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LoginFilter implements Filter {
	private String filterPattern;

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String loginAdmin = (String) request.getSession().getAttribute("LOGIN_ADMIN");
		String requestURI = request.getRequestURI();
		String page = requestURI.substring(request.getContextPath().length());
		
		if(page.matches(filterPattern)){
			if(loginAdmin == null && !page.equals("/backend/login.jsp")
					&& !page.equals("/backend/LoginServlet")){
				response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		filterPattern = fConfig.getInitParameter("filterPattern");
		
	}

	@Override
	public void destroy() {
		
	}
}
