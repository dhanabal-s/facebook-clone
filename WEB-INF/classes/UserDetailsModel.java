package connect.model;

import lombok.Data;

@Data
public class UserDetailsModel {
	private String userName;
	private String userPassword;
	private String userMailId;
	private String userProfilePicPath;
	

	// public void setUserName(String userName) {
	// 	this.userName = userName;
	// }
	

	// public void setUserPassword(String userPassword) {
	// 	this.userPassword = userPassword;
	// }


	// public void setUserMailId(String userMailId) {
	// 	this.userMailId = userMailId;
	// }

	// public String getUserName() {
	// 	return this.userName;
	// } 

	// public String getUserPassword() {
	// 	return this.userPassword;
	// }

	// public String getUserMailId() {
	// 	return this.userMailId;
	// }

}