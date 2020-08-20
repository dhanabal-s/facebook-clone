package connect.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class LoginCheckFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setDateHeader("Expires", 0);
		if(session.getAttribute("userId") == null) {
				res.sendRedirect("index.jsp");
		} else {
			chain.doFilter(req,res);
		}


	}
}