package connect.servlet;

import java.io.*;
import java.net.URLDecoder;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.db.dao.FriendDao;


public class AddFriend extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession();

		URLDecoder.decode(req.getQueryString(),"UFT-8");
		
		int userId = (int)session.getAttribute("userId");
		int friendId = Integer.parseInt(req.getParameter("friendid"));

		FriendDao.addFriend(userId, friendId);

		PrintWriter out = res.getWriter();
		out.print("Success");
	}
}