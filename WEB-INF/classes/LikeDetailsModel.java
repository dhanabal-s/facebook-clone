package connect.model;


public class LikeDetailsModel {

	private String userName;
	private String userProfilePicPath;
	

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserProfilePicPath(String userProfilePicPath) {
		this.userProfilePicPath = userProfilePicPath;
	}


	public String getUserName() {
		return userName;
	}

	public String getUserProfilePicPath() {
		return userProfilePicPath;
	}
}