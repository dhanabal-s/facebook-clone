package connect.servlet;

import java.util.*;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connect.db.dao.LikeDao;
import connect.db.dao.PostDao;
import connect.model.PostModel;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HomePage extends HttpServlet {


    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        HttpSession session = req.getSession();
        Logger log = Logger.getLogger("com.my.connection");
        int userId = (int) session.getAttribute("userId");
        
        ArrayList<PostModel> post = PostDao.getPost(userId);
        PrintWriter out = res.getWriter();

        ObjectMapper mapper = new ObjectMapper();
        String postJson = mapper.writeValueAsString(post);
        log.info(postJson);
        out.print(postJson);
        out.flush();
        // session.setAttribute("post", post);

        // Set<Integer> likedPost = LikeDao.getLikedPostList(userId);
        // session.setAttribute("likedPost",likedPost);

        // RequestDispatcher rd = req.getRequestDispatcher("homePage.jsp");
        // rd.forward(req, res);
        
    }
}