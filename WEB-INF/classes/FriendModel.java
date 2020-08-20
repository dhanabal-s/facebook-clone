package connect.model;

import java.io.Serializable;
import lombok.Setter;
import lombok.Getter;


@Setter
@Getter
public class FriendModel {
	private int id;
	private int friendId;
	private String friendName;
	private String friendCountry;
	private String friendWork;
	private String friendProfilePicPath;
}