package connect.servlet;

import java.io.*;
import java.net.URLDecoder;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.db.dao.CommentDao;
import connect.model.CommentDetailsModel;


public class AddComment extends HttpServlet {

	private Logger logger = Logger.getLogger("com.my.connection");

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		logger.info("<>Inside AddComment Class");

		HttpSession session = req.getSession();
		URLDecoder.decode(req.getQueryString(),"UTF-8");
		
		int postId = Integer.parseInt(req.getParameter("postId"));
		int postPublisherId = Integer.parseInt(req.getParameter("postPublisherId"));
		int commentParentId = Integer.parseInt(req.getParameter("commentParentId"));
		int commentPublisherId = (int)session.getAttribute("userId");
		String commentContent = req.getParameter("commentcontent").toString();

		CommentDetailsModel model = new CommentDetailsModel();
		model.setPostId(postId);
		model.setPostPublisherId(postPublisherId);
		model.setCommentParentId(commentParentId);
		model.setCommentPublisherId(commentPublisherId);
		model.setCommentContent(commentContent);


		int commentId = CommentDao.getCommentId(model) + 1;
		model.setCommentId(commentId);

		CommentDao.addComment(model);

		PrintWriter out = res.getWriter();
		out.print("Success");
		out.flush();
	}
}