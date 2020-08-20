package connect.servlet;

import java.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.db.dao.LikeDao;
import connect.model.LikeDetailsModel;


public class LikeDetails extends HttpServlet {


	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		int postId = Integer.parseInt(req.getParameter("postId"));

		ArrayList<LikeDetailsModel> model = LikeDao.getLikeDetails(postId);

		HttpSession session = req.getSession();

		session.setAttribute("likeDetail",model);

		RequestDispatcher rs = req.getRequestDispatcher("likeDetails.jsp");
		rs.forward(req,res);

	}
} 