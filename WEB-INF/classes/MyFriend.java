package connect.servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.model.FriendModel;
import connect.db.dao.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MyFriend extends HttpServlet {


	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		Logger log = Logger.getLogger("com.my.connection");
		log.info("<>Inside MyFriends Servlet class");

		HttpSession session = req.getSession();
		int userId = (int)session.getAttribute("userId");
		ArrayList<FriendModel> friendList = UserDao.getFriendList(userId, "MyFriends");
		session.setAttribute("friendList",friendList);
		
		ObjectMapper mapper = new ObjectMapper();
		String responseJson = mapper.writeValueAsString(friendList);

		PrintWriter out = res.getWriter();
		log.info(responseJson);
		out.print(responseJson);
		// RequestDispatcher rd = req.getRequestDispatcher("friends.jsp");
		// rd.forward(req,res);
	}
}