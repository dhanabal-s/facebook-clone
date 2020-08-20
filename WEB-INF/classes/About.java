package connect.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.model.PersonalDetailsModel;
import connect.db.dao.UserDao;


public class About extends HttpServlet {


	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException { 

		HttpSession session = req.getSession();
		int userId = (int)session.getAttribute("userId");
		
		PersonalDetailsModel profile = UserDao.getPersonalDetails(userId);
		session.setAttribute("personalDetails",profile);

		RequestDispatcher rd = req.getRequestDispatcher("about.jsp");
		rd.forward(req,res);
	}
}