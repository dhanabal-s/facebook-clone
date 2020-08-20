package connect.filter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class NullCheckFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String name = req.getParameter("name");
		String mail = req.getParameter("email");
		String password = req.getParameter("password");

		if(isInValid(name) || isInValid(mail) || isInValid(password)) {
			res.sendRedirect("index.jsp");

		} else {
			chain.doFilter(req,res);
		}
	}


	boolean isInValid(String text) {

		return text.equals("");
	}
}