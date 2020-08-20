package connect.servlet;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.io.*; 
import java.net.URLDecoder;
import javax.servlet.*;
import javax.servlet.http.*;
import connect.db.dao.UserDao;
import connect.model.PersonalDetailsModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDetail extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Logger log = Logger.getLogger("com.my.connection");
		log.info("<>inside UserDetail Servlet Class()");
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");

		PersonalDetailsModel personalDetails = UserDao.getPersonalDetails(userId);

		ArrayList<PersonalDetailsModel> model = new ArrayList<PersonalDetailsModel>();
		model.add(personalDetails);

		ObjectMapper mapper = new ObjectMapper();
		String responseJson = mapper.writeValueAsString(model);

		PrintWriter out = response.getWriter();
		log.info(responseJson);
		out.print(responseJson); 
	}		
}