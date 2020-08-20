package connect.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import lombok.Data;

@Data
public class PostModel implements Serializable {

	private int id;
	private int userId;
	private String userName;
	private int postId;
	private Timestamp timestamp;
	private String content;
	private String profilePicPath;
	private String postFilePath;
	private int likeCount;
	private Boolean isLiked;
	private ArrayList<CommentDetailsModel> comments;

	// public void setUserId(int id) {
	// 	this.userId = id;
	// }

	// public void setUserName(String name) {
	// 	this.userName = name;
	// }

	// public void setPostId(int id) {
	// 	this.postId = id;
	// }

	// public void setTimestamp(Timestamp ts) {
	// 	this.timestamp = ts;
	// }

	// public void setContent(String text) {
	// 	this.content = text;
	// }

	// public void setLikeCount(int likeCount) {
	// 	this.likeCount = likeCount;
	// }

	// public void setProfilePicPath(String profilePicPath) {
	// 	this.profilePicPath = profilePicPath;
	// }

	// public void setPostFilePath(String postFilePath) {
	// 	this.postFilePath = postFilePath;
	// }


	// public int getUserId() {
	// 	return userId;
	// }

	// public String getUserName() {
	// 	return userName;
	// }

	// public int getPostId() {
	// 	return postId;
	// }

	// public Timestamp getTimestamp() {
	// 	return timestamp;
	// }

	// public String getContent() {
	// 	return content;
	// }

	// public int getLikeCount() {
	// 	return likeCount;
	// }

	// public String getProfilePicPath() {
	// 	return profilePicPath;
	// }

	// public String getPostFilePath() {
	// 	return postFilePath;
	// }

}