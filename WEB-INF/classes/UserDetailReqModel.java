package connect.model;

import lombok.Data;

@Data
public class UserDetailReqModel {
	private String userName;
	private String userProfilePicPath;
	private String userCoverPicPath;
}