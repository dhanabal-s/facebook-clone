package connect.model;

import java.sql.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentDetailsModel {

	private String id;
	private int postId;
	private int postPublisherId;
	private int commentPublisherId;
	private int commentParentId;
	private int commentId;
	private int likeCount;
	private String commentPublisherName;
	private String commentContent;
	private String commentPublisherPicPath;
	private Timestamp commentTime;


	// public void setPostId(int postId) {
	// 	this.postId = postId;
	// }

	// public void setPostPublisherId(int postPublisherId) {
	// 	this.postPublisherId = postPublisherId;
	// }

	// public void setCommentPublisherId(int commentPublisherId) {
	// 	this.commentPublisherId = commentPublisherId;
	// }

	// public void setCommentParentId(int commentParentId) {
	// 	this.commentParentId = commentParentId;
	// }

	// public void setCommentId(int commentId) {
	// 	this.commentId = commentId;
	// }

	// public void setCommentContent(String commentContent) {
	// 	this.commentContent = commentContent;
	// }


	// public int getPostId() {
	// 	return postId;
	// }

	// public int getPostPublisherId() {
	// 	return postPublisherId;
	// }

	// public int getCommentPublisherId() {
	// 	return commentPublisherId;
	// }

	// public int getCommentParentId() {
	// 	return commentParentId;
	// }

	// public int getCommentId() {
	// 	return commentId;
	// }

	// public String getCommentContent() {
	// 	return commentContent;
	// } 
}