package connect.servlet;

import java.util.HashMap;
import java.io.*;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import connect.db.dao.ValidationDao;
import connect.db.dao.UserDao;
import connect.model.LoginResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Login extends HttpServlet{
    
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        String userMail = req.getParameter("loginemail");
        String password = req.getParameter("loginpassword");
        PrintWriter out = res.getWriter();
        LoginResponseModel responseModel = new LoginResponseModel();

        if(ValidationDao.isMatch(userMail,password)) {
          
          int userId = UserDao.getUserId(userMail);
          HttpSession session = req.getSession();
          session.setAttribute("userId",userId);
          String userName = UserDao.getUserName(userId);
          session.setAttribute("userName",userName);
          HashMap<String,String> picturePath = UserDao.getPicturePath(userId);
          session.setAttribute("profilePicPath",picturePath.get("profilePicPath"));
          session.setAttribute("coverPicPath",picturePath.get("coverPicPath"));
          responseModel.setMessage("match");
          // res.sendRedirect("homepage");
        } else {
         responseModel.setMessage("mismatch");
          // res.sendRedirect("login.jsp");
        }

      ObjectMapper mapper = new ObjectMapper();
      String responseMsg =  mapper.writeValueAsString(responseModel);
      out.print(responseMsg);
    }
    
  
}
