package connect.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.model.UserDetailsModel;
import connect.db.dao.UserDao;
import connect.db.dao.ValidationDao;   
import connect.model.LoginResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Signup extends HttpServlet {
    

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException	 {
        
        String password = req.getParameter("password");
        // String confirmPassword = req.getParameter("confirmPassword");
        HttpSession session = req.getSession();
        PrintWriter out = res.getWriter();
        LoginResponseModel responseModel = new LoginResponseModel();

        // if(password.equals(confirmPassword)){
    	String userMail = req.getParameter("email");
    	String userName = req.getParameter("name");

    	if(!ValidationDao.isExist(userMail)) {
    		
    		UserDetailsModel userDetails = new UserDetailsModel();
    		userDetails.setUserName(userName);
    		userDetails.setUserPassword(password);
    		userDetails.setUserMailId(userMail);

    		UserDao.addUser(userDetails);
            int userId = UserDao.getUserId(userMail);
            session.setAttribute("userId",userId);
            session.setAttribute("userName",userName); 
    		// res.sendRedirect("homepage");
            responseModel.setMessage("success");
    	} else {
    		
    		// RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
    	 //    rd.forward(req,res);
      //       session.setAttribute("error","Email already exists!!!");
            responseModel.setMessage("Email already Exists");
    	  }

        ObjectMapper mapper = new ObjectMapper();
        String responseMsg =  mapper.writeValueAsString(responseModel);
        out.print(responseMsg);

        // } else {
        	
        // 	RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        // 	rd.forward(req,res);
        //     session.setAttribute("error","Password and Confirm Password are mismath!!!");
        // }
    
    }
    

}