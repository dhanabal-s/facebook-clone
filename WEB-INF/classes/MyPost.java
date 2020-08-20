package connect.servlet;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.db.dao.LikeDao;
import connect.db.dao.PostDao;
import connect.model.PostModel;
import connect.model.PersonalDetailsModel;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MyPost extends HttpServlet {

	private Logger log = Logger.getLogger("com.my.connection");

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		log.info("<>Insode MyPost class");
		HttpSession session = req.getSession();
		int userId = (int)session.getAttribute("userId");
		
		ArrayList<PostModel> postList = PostDao.getMyPostList(userId);

		session.setAttribute("postList",postList);

		// Set<Integer> likedPostList = LikeDao.getLikedPostList(userId);
		// session.setAttribute("likedPost",likedPostList);

		ObjectMapper mapper = new ObjectMapper();
		String responseJson = mapper.writeValueAsString(postList);
		PrintWriter out = res.getWriter();
		out.print(responseJson);
		// RequestDispatcher rd = req.getRequestDispatcher("post.jsp");
		// rd.forward(req,res);
	}
}