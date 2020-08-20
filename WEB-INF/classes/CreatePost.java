package connect.servlet;

import java.io.IOException;
import java.io.File;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import connect.db.dao.PostDao;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class CreatePost extends HttpServlet {

	private final static String uploadDIR = "C:\\Users\\Dhanabal\\Desktop\\apache-tomcat-9.0.35\\webapps\\connection\\client\\uploaded";

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String filePath = null;
		
		File uploadDIRCheck = new File(uploadDIR);
		
		if(!uploadDIRCheck.exists()) {
			uploadDIRCheck.mkdir();
		}

		if(req.getPart("postFile").getSize()>0) {
			for (Part part : req.getParts()) {

				String givenFileName = getFileName(part);
				
				if( givenFileName != null && givenFileName !="") {

					// String fileType = getFileType(givenFileName);
					String fileType = givenFileName.substring(givenFileName.indexOf("."), givenFileName.length()); 
					String fileName = (PostDao.getLastPostId()+1)+fileType;
					filePath = uploadDIR+File.separator+fileName;
					part.write(filePath);
				}
			}
		}
		HttpSession session = req.getSession();
		int userId = (int)session.getAttribute("userId");
		String postContent = req.getParameter("postContent");

		PostDao.addPost(userId,postContent,filePath);
		// res.sendRedirect("homepage");
	
	 }


	private String getFileName(Part part) {

		for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename"))
	            return content.substring(content.indexOf("=") + 2, content.length() - 1);
	        }
	    return null;
	}


	private String getFileType(String fileName) {

		return fileName.substring(fileName.indexOf("."),fileName.length());
	}

}