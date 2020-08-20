package connect.db.dao;

import java.util.*;
import java.util.logging.Logger;
import java.sql.*;
import connect.model.LikeDetailsModel;
import connect.db.util.DBConnection;
import connect.model.LikeModel;

public class LikeDao {

	private static Logger logger = Logger.getLogger("com.my.connection");

	private static Connection con;


	public static LikeModel liker(int userId, int postId) {

		LikeModel likeModel = new LikeModel();
		
		try {
			con = DBConnection.getConnection();
			logger.info("<>Inside LikeDao class liker Method()");
			
			boolean alreadyLiked = isLiked(userId,postId);


			if(alreadyLiked){
				removeLike(userId,postId);
				likeModel.setIsLiked(false);
			} else {
				addLike(userId,postId);
				likeModel.setIsLiked(true);
			}

			String qry = " SELECT COUNT(*) AS TOTAL_LIKE FROM LIKES WHERE POST_ID = ? ";
			PreparedStatement preStmt = con.prepareStatement(qry);
			preStmt.setInt(1, postId);

			ResultSet rs = preStmt.executeQuery();

			if(rs.next()) {
				likeModel.setTotalLike(rs.getInt("TOTAL_LIKE"));
			}

			return likeModel;

		} catch (Exception ex) {
			logger.severe("<>"+ex.getMessage());
		} finally {
			if(con!= null){
				try {
					con.close();
				} catch(Exception ex){}
			}
		}
		return likeModel;
	}


	private static boolean isLiked(int userId, int postId) throws SQLException {

		logger.info("<>Inside isLiked Method()");

		String selQry = "SELECT POST_ID,USER_ID FROM LIKES WHERE USER_ID=? AND POST_ID=?";
		PreparedStatement preStmt = con.prepareStatement(selQry);
		preStmt.setInt(1,userId);
		preStmt.setInt(2,postId);

		ResultSet rs = preStmt.executeQuery();

		if(rs.next()) {
			return true;
		}

		return false;
		
	}


	private static void addLike(int userId, int postId) {

		try {

			logger.info("<>Inside addLike Method()");
			String insQry = " INSERT INTO LIKES ( USER_ID , POST_ID ) VALUES ( ? , ? ) ";
			PreparedStatement preStmt = con.prepareStatement(insQry);
			preStmt.setInt(1,userId);
			preStmt.setInt(2,postId);

			preStmt.executeUpdate();
		} catch(Exception ex) {
			logger.severe("<>"+ex.getMessage());
		}
	}


	private static void removeLike(int userId, int postId) throws SQLException {

		try {

			logger.info("<>Inside removeLike Method()");
			String delQry = " DELETE FROM LIKES WHERE USER_ID = ? AND POST_ID = ? ";
			PreparedStatement preStmt = con.prepareStatement(delQry);
			preStmt.setInt(1,userId);
			preStmt.setInt(2,postId);

			preStmt.executeUpdate();
		} catch(Exception ex) {
			logger.severe("<>"+ex.getMessage());
		}
	}


	public static ArrayList<LikeDetailsModel> getLikeDetails(int postId) {

		ArrayList<LikeDetailsModel> likeDetails = new ArrayList<LikeDetailsModel>();

		try {

			logger.info("<>Inside LikeDao class getLikeDetails Method()");
			Connection con = DBConnection.getConnection();

			String selQry = " SELECT UD.USER_NAME, UD.USER_PROFILE FROM USER_DETAILS AS UD " +
							" LEFT JOIN " +
							" LIKES AS LI ON LI.USER_ID = UD.USER_ID WHERE LI.POST_ID = ? ORDER BY UD.USER_NAME ";

			PreparedStatement preStmt = con.prepareStatement(selQry);
			preStmt.setInt(1,postId);

			ResultSet rs = preStmt.executeQuery();

			while(rs.next()) {

				LikeDetailsModel detail = new LikeDetailsModel();
				detail.setUserName(rs.getString("USER_NAME"));
				detail.setUserProfilePicPath(rs.getString("USER_PROFILE"));
				likeDetails.add(detail); 
			}

		} catch(Exception ex) {
			logger.severe("<>"+ex.getMessage());
		}

		return likeDetails;
	}



	public static HashSet<Integer> getLikedPostList(int userId) {

		logger.info("<>Inside LikeDao class getLikedPostList Method()");

		HashSet<Integer> likedPost = new HashSet<Integer>();

		try {

			con = DBConnection.getConnection();

			String selQry = " SELECT POST_ID FROM LIKES WHERE USER_ID = ? ";

			PreparedStatement preStmt = con.prepareStatement(selQry);
			preStmt.setInt(1,userId);

			ResultSet rs = preStmt.executeQuery();

			while(rs.next()) {

				likedPost.add(rs.getInt("POST_ID"));
			}

		} catch(Exception ex) {

			logger.severe("<>"+ex.getMessage());
		} finally {
			if(con!= null){
				try {
					con.close();
				} catch(Exception ex){}
			}
		}

		return likedPost;
	}
}