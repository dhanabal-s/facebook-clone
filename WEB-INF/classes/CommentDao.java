package connect.db.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import lombok.Cleanup;
import connect.db.util.DBConnection;
import connect.model.CommentDetailsModel;


// @Cleanup 
public class CommentDao {

	private static Logger log = Logger.getLogger("com.my.connection");
	// private static Connection con;

	public static void addComment(CommentDetailsModel model) {

		try {

			log.info("<>Inside CommentDao Class addComment() Method");

			Connection con = DBConnection.getConnection();

			String insQry = " INSERT INTO COMMENT(POST_ID, POST_PUBLISHER_ID, COMMENT_PUBLISHER_ID, COMMENT_PARENT_ID, COMMENT_ID, COMMENT_CONTENT) " +
							" VALUES(?,?,?,?,?,?) ";

			PreparedStatement stmt = con.prepareStatement(insQry);
			stmt.setInt(1, model.getPostId());
			stmt.setInt(2, model.getPostPublisherId());
			stmt.setInt(3, model.getCommentPublisherId());
			stmt.setInt(4, model.getCommentParentId());
			stmt.setInt(5, model.getCommentId());
			stmt.setString(6, model.getCommentContent());

			stmt.executeUpdate();
		} catch(Exception ex) {

			log.severe("<>"+ex.getMessage());
		}
	}


	public static int getCommentId(CommentDetailsModel model) {

		int commentCount = 0;

		try {

			log.info("<>Inside CommentDao Class getCommentId() Method");

			Connection con = DBConnection.getConnection();

			String selQry = " SELECT COUNT(*) AS COMMENTCOUNT FROM COMMENT " +
							" WHERE " +
							" POST_ID = ? AND POST_PUBLISHER_ID = ? ";

			PreparedStatement stmt = con.prepareStatement(selQry);
			stmt.setInt(1, model.getPostId());
			stmt.setInt(2, model.getPostPublisherId());
			// stmt.setInt(3, model.getCommentParentId());

			ResultSet rs = stmt.executeQuery(); 

			if(rs.next()) {

				commentCount = rs.getInt("COMMENTCOUNT");
			}
		} catch(Exception ex) {

			log.severe("<>"+ex.getMessage());
		}

		return commentCount;
	}


	public static ArrayList<CommentDetailsModel> getCommentDetails(int postId, int postPublisherId) {

		ArrayList<CommentDetailsModel> commentDetails = new ArrayList<CommentDetailsModel>();

		try {

			log.info("<>Inside CommentDao Class getCommentDetails() Method");

			Connection con = DBConnection.getConnection();

			String selQry = " SELECT COM.POST_ID AS PO_ID, COM.POST_PUBLISHER_ID AS PO_PUB_ID, COM.COMMENT_ID AS C_ID, " +
							" COM.COMMENT_PUBLISHER_ID AS C_PUB_ID, "+ 
							" COM.COMMENT_PARENT_ID AS C_PAR_ID, COM.COMMENT_CONTENT AS C_CONTENT, COM.COMMENT_TIME AS C_TIME, " +
							" USER.USER_NAME AS U_NAME, USER.USER_PROFILE AS U_PIC FROM COMMENT AS COM " +
							" INNER JOIN " +
							" USER_DETAILS AS USER ON COM.COMMENT_PUBLISHER_ID = USER.USER_ID "+
							" WHERE POST_ID = ? AND POST_PUBLISHER_ID = ? ORDER BY C_TIME DESC";

			PreparedStatement stmt = con.prepareStatement(selQry);
			stmt.setInt(1, postId);
			stmt.setInt(2, postPublisherId);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {

				CommentDetailsModel model = new CommentDetailsModel();
				model.setId(rs.getInt("PO_ID")+""+rs.getInt("C_ID"));
				model.setCommentId(rs.getInt("C_ID"));
				model.setPostPublisherId(rs.getInt("PO_PUB_ID"));
				model.setPostId(rs.getInt("PO_ID"));
				model.setCommentPublisherId(rs.getInt("C_PUB_ID"));
				model.setCommentPublisherName(rs.getString("U_NAME"));
				model.setCommentContent(rs.getString("C_CONTENT"));
				model.setCommentPublisherPicPath(rs.getString("U_PIC"));
				model.setCommentTime(rs.getTimestamp("C_TIME"));
				commentDetails.add(model);
			}

		} catch (Exception ex) {

			log.severe("<>"+ex.getMessage());
		}

		return commentDetails;
	}
}