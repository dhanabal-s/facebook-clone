package connect.servlet;

import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Logout extends HttpServlet {

	private Logger logger = Logger.getLogger("com.my.connection");


	public void service(HttpServletRequest req, HttpServletResponse res) {

		logger.info("<>Inside Logout class");

		try {

			HttpSession session = req.getSession();
			session.removeAttribute("userId");
			session.invalidate();
			logger.info("<>Invalidate the session and successfully!!!");
			// res.sendRedirect("index.jsp");
		} catch(Exception ex) {

			logger.severe("<>"+ex.getMessage());
		}
	}
}
