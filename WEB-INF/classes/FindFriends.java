package connect.servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.db.dao.UserDao;
import connect.model.FriendModel;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FindFriends extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Logger log = Logger.getLogger("com.my.connection");
		log.info("<>Inside FindFriends servlet Class()");
		HttpSession session = req.getSession();

		int userId = (int)session.getAttribute("userId");
		ArrayList<FriendModel> friendSugList = UserDao.getFriendList(userId,"FriendSug");

		session.setAttribute("friendSugList",friendSugList);

		PrintWriter out = res.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String responseJson = mapper.writeValueAsString(friendSugList);
		
		log.info(responseJson);
		out.print(responseJson);
		out.flush();
		// RequestDispatcher rd = req.getRequestDispatcher("friendSug.jsp");
		// rd.forward(req,res);
	}
}