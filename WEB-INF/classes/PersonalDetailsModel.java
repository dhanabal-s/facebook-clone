package connect.model;

import lombok.Data;

@Data
public class PersonalDetailsModel {
	
	private int id;
	private int userId;
	private String userName;
	private String userMailId;
	private String userCountry;
	private String userWork;
	private String userDOB;
	private String userProfilePicPath;
	private String userCoverPicPath;


	// public void setUserName(String userName) {
	// 	this.userName = userName; 
	// }

	// public void setUserMailId(String userMailId) {
	// 	this.userMailId = userMailId;
	// }

	// public void setUserDOB(String userDOB) {
	// 	this.userDOB = userDOB;
	// }

	// public void setUserWork(String work) {
	// 	this.userWork = work;
	// }

	// public void setUserCountry(String country) {
	// 	this.userCountry = country;
	// }


	// public String getUserName() {
	// 	return userName;
	// }

	// public String getUserMailId() {
	// 	return userMailId;
	// }

	// public String getUserDOB() {
	// 	return userDOB;
	// }

	// public String getUserWork() {
	// 	return userWork;
	// }

	// public String getUserCountry() {
	// 	return userCountry;
	// }
}