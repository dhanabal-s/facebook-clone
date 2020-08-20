package connect.servlet;

import java.io.*;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.net.URLDecoder;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.db.dao.CommentDao;
import connect.model.CommentDetailsModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FetchComment extends HttpServlet {


	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		Logger log = Logger.getLogger("com.my.connection");
		log.info("<>Inside FetchComment Servlet Class");

		HttpSession session = req.getSession();
		String decode = URLDecoder.decode(req.getQueryString(),"UTF-8");

		int pId = Integer.parseInt(req.getParameter("postid"));
		int postPublisherId = Integer.parseInt(req.getParameter("userid"));

		ArrayList<CommentDetailsModel> commentDetails = CommentDao.getCommentDetails(pId,postPublisherId);
		
		ObjectMapper mapper = new ObjectMapper();
		String responseJson = mapper.writeValueAsString(commentDetails);

		PrintWriter out = res.getWriter();
		
		log.info(responseJson);
		out.print(responseJson);
		out.flush();
		// session.setAttribute("commentDetails",commentDetails);
		// res.sendRedirect("showComments.jsp");
	}
}