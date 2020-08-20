package connect.db.dao;

import java.util.logging.*;
import java.sql.*;
import connect.db.util.DBConnection;


public class ValidationDao {

	private static Logger logger = Logger.getLogger("com.my.connection");

	public static boolean isMatch(String userMail, String password) {

		logger.info("<>Inside ValidationDao class isMatch method ()");

		try {

			Connection con = DBConnection.getConnection();
			String checkQry = " SELECT USER_ID FROM USER_DETAILS WHERE USER_MAIL = ? AND USER_PASSWORD=? ";
			PreparedStatement preStmt = con.prepareStatement(checkQry);
			preStmt.setString(1,userMail);
			preStmt.setString(2,password);

			ResultSet rs = preStmt.executeQuery();

			if(rs.next()) {
				logger.info("<>Given details are Matched!!!");
				return true;
			}

		} catch(Exception ex) {
			logger.severe("<>"+ex.getMessage());
		}

		logger.info("<>Given details are not Match!!!");
		return false;
	}


	
	public static boolean isExist(String userMail) {

		logger.info("<>Inside ValidationDao class isExist method()");

		try {

            Connection con = DBConnection.getConnection();
            String checkQry = " SELECT USER_ID FROM USER_DETAILS WHERE USER_MAIL = ? ";
            PreparedStatement preStmt = con.prepareStatement(checkQry);
            preStmt.setString(1,userMail);
            
            ResultSet rs = preStmt.executeQuery();
        
            if(rs.next()) {
            	logger.info("<>Given MailId is exist!!!");
            	return true;
            } 

		} catch(Exception ex) {
			logger.severe("<>"+ex.getMessage());
		} 

    	logger.info("<>Given MailId is not exists!!");
    	return false;
	}


}