package connect.servlet;

import java.util.logging.Logger;
import java.io.*; 
import java.net.URLDecoder;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.db.dao.LikeDao;
import connect.model.LikeModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Like extends HttpServlet {


	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession();
		Logger logger = Logger.getLogger("com.my.connection");
		logger.info("<>Inside Like Class");
		URLDecoder.decode(req.getQueryString(),"UTF-8");
		int postId = Integer.parseInt(req.getParameter("postId"));
		int userId = (int)session.getAttribute("userId");

		LikeModel likeModel = LikeDao.liker(userId,postId);
		
		ObjectMapper mapper = new ObjectMapper();
		String processedVal =  mapper.writeValueAsString(likeModel);
		PrintWriter out = res.getWriter();
		out.print(processedVal);
		out.flush();
		// res.sendRedirect("homepage");
	}
}