package connect.db.dao;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.logging.*;
import java.sql.*;
import connect.model.UserDetailsModel;
import connect.model.ProfileModel;
import connect.model.FriendModel;
import connect.model.PostModel;
import connect.model.PersonalDetailsModel;
import connect.db.util.DBConnection;


public class UserDao {


	private static Logger logger = Logger.getLogger("com.my.connection");

	public static void addUser(UserDetailsModel user) {

		logger.info("<>Inside UserDao class addUser method()");

		try {

			Connection con = DBConnection.getConnection();

			String insQry = " INSERT INTO USER_DETAILS(USER_MAIL,USER_NAME,USER_PASSWORD) VALUES(?,?,?) "; 
			PreparedStatement  preStmt = con.prepareStatement(insQry);
			preStmt.setString(1,user.getUserMailId());
			preStmt.setString(2,user.getUserName());
			preStmt.setString(3,user.getUserPassword());
			preStmt.executeUpdate();
			logger.info("<>User Signup Successfullly!!!");

		} catch(Exception ex) {

			logger.severe("<>"+ex.getMessage());
		}
	}


	
	public static int getUserId(String userMail) {

		logger.info("<>Inside UserDao class getUserId method()");
	
		try {

		    Connection con = DBConnection.getConnection();
		    
		    String selQry = " SELECT USER_ID FROM USER_DETAILS WHERE USER_MAIL = ? ";

     		PreparedStatement preStmt = con.prepareStatement(selQry);
	    	preStmt.setString(1,userMail);
		    ResultSet rs = preStmt.executeQuery();
		
	    	if(rs.next()) {
	    		logger.info("<>Getting User Id for given Mail Id!!!");
		    	return rs.getInt("USER_ID");
		    }


		} catch (Exception ex) {

			logger.severe("<>"+ex.getMessage());
		}

		logger.warning("<>Given Mail Id Not Exist!!!");
		return 0;

	}



	public static String getUserName(int userId) {

		logger.info("<>Inside UserDao class getUserName method()");

		try {

			Connection con = DBConnection.getConnection();

			String selQry = " SELECT USER_NAME  FROM USER_DETAILS WHERE USER_ID = ? ";
			PreparedStatement preStmt = con.prepareStatement(selQry);
			preStmt.setInt(1,userId);
			ResultSet rs = preStmt.executeQuery();

			if(rs.next()) {
				logger.info("<>Getting User Name for given Uesr Id!!!");
				return rs.getString("USER_NAME");
			}
		} catch(Exception ex) {
			logger.severe("<>"+ex.getMessage());
		}

		logger.warning("<>Given User Id Not Exist!!!");
		return "";
	}


	public static PersonalDetailsModel getPersonalDetails(int userId) {

		logger.info("<>Inside UserDao class getUserProfile method()");
		PersonalDetailsModel personalDetail = new PersonalDetailsModel();

		try {

			Connection con = DBConnection.getConnection();

			logger.info("<>Start fetching user details "+userId);

			String selProfile = " SELECT USER_ID, USER_NAME, USER_MAIL, DATE_FORMAT(USER_DOB,'%d/%m/%Y') AS USER_DOB," +
								" USER_WORK, USER_COUNTRY, USER_PROFILE, USER_COVER_PROFILE FROM USER_DETAILS" +
								" WHERE USER_ID = ? ";
			PreparedStatement profileStmt = con.prepareStatement(selProfile);
			profileStmt.setInt(1,userId);

			ResultSet rs = profileStmt.executeQuery();


			if(rs.next()) {
				logger.info("<>User details fetched.");
				personalDetail.setId(rs.getInt("USER_ID"));
				personalDetail.setUserId(rs.getInt("USER_ID"));
				personalDetail.setUserName(rs.getString("USER_NAME")!=null?rs.getString("USER_NAME"):"");
				personalDetail.setUserProfilePicPath(rs.getString("USER_PROFILE"));
				personalDetail.setUserCoverPicPath(rs.getString("USER_COVER_PROFILE"));
				personalDetail.setUserMailId(rs.getString("USER_MAIL")!=null?rs.getString("USER_MAIL"):"");
				personalDetail.setUserDOB(rs.getString("USER_DOB")!=null?rs.getString("USER_DOB"):"");
				personalDetail.setUserWork(rs.getString("USER_WORK")!=null?rs.getString("USER_WORK"):"");
				personalDetail.setUserCountry(rs.getString("USER_COUNTRY")!=null?rs.getString("USER_COUNTRY"):"");
			}
	
			

		} catch(Exception ex) {
			logger.severe(ex.toString());
		}

		return personalDetail;
	}



	public static ArrayList<FriendModel> getFriendList(int userId, String friendOption) {

		logger.info("<>Inside UserDao Class getFriendList Method()");
		ArrayList<FriendModel> friendList = new ArrayList<FriendModel>();

		try {

			Connection con = DBConnection.getConnection();

			logger.info("<>Fetching user Friends List");

			String selFriend;

			if(friendOption.equals("MyFriends")) {

				selFriend =	" SELECT UD.USER_ID, UD.USER_NAME, UD.USER_COUNTRY, USER_WORK, USER_PROFILE FROM USER_DETAILS AS UD "+
							" WHERE " +
							" UD.USER_ID IN " +
							" ( " + 
							" SELECT FL.UID1 FROM FRIEND_LIST AS FL WHERE FL.UID2 = ? " +
							" ) " +
							" OR " +
							" UD.USER_ID IN " +
							" ( " +
							" SELECT FL.UID2 FROM FRIEND_LIST AS FL WHERE FL.UID1 = ? " +
							" ) " +
							" AND " +
							" USER_ID != ? ";
			} else {

				selFriend = " SELECT UD.USER_ID, UD.USER_NAME, UD.USER_COUNTRY, USER_WORK, USER_PROFILE FROM USER_DETAILS AS UD " +
							" WHERE UD.USER_ID " +
							" NOT IN " +
							" ( " +
							" SELECT UD.USER_ID FROM USER_DETAILS AS UD  WHERE  UD.USER_ID  IN " +
							" ( " +
							" SELECT FL.UID1 FROM FRIEND_LIST AS FL WHERE FL.UID2 = ? " +
							" ) " +
							" OR " +
							" UD.USER_ID IN " +
							" ( " +
							" SELECT FL.UID2 FROM FRIEND_LIST AS FL WHERE FL.UID1 = ? " +
							" ) " +
							" ) " +
							" AND " +
							" USER_ID != ? ";
			}

			PreparedStatement friendStmt = con.prepareStatement(selFriend);
			friendStmt.setInt(1, userId);
			friendStmt.setInt(2, userId);
			friendStmt.setInt(3, userId);

			ResultSet rs2 = friendStmt.executeQuery();

			logger.info("<>Getting user Friends Details");
			while(rs2.next()) {
				FriendModel friendModel = new FriendModel();
				friendModel.setId(rs2.getInt("USER_ID"));
				friendModel.setFriendId(rs2.getInt("USER_ID"));
				friendModel.setFriendName(rs2.getString("USER_NAME"));
				friendModel.setFriendCountry(rs2.getString("USER_COUNTRY"));
				friendModel.setFriendWork(rs2.getString("USER_WORK"));
				friendModel.setFriendProfilePicPath(rs2.getString("USER_PROFILE"));
				friendList.add(friendModel);
			}
		} catch(Exception ex) {
			logger.info("<>"+ex.getMessage());
		}
		return friendList;
	}



	public static HashMap<String,String> getPicturePath(int userId) {


		HashMap<String,String> picturePath = new HashMap<String,String>();
		try {

			logger.info("<>Inside UserDao Class getUserProfilePicPath Method()");
			Connection con = DBConnection.getConnection();

			String selQry = "SELECT USER_PROFILE,USER_COVER_PROFILE FROM USER_DETAILS WHERE USER_ID = ?";

			PreparedStatement stmt = con.prepareStatement(selQry);
			stmt.setInt(1,userId);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				logger.info("<>Sending user Picture Path");
				picturePath.put("profilePicPath",rs.getString("USER_PROFILE"));
				picturePath.put("coverPicPath",rs.getString("USER_COVER_PROFILE"));
			} else {
				logger.info("<>User not Set the Profile pic");
				picturePath.put("profilePicPath",null);
				picturePath.put("coverPicPath",null);
			}
		} catch(Exception ex) {
			logger.severe("<>"+ex.getMessage());
		}

		return picturePath;
	}

}