package connect.model;

import java.sql.Date;
import java.util.*;
import lombok.Data;

@Data
public class ProfileModel {
	
	private PersonalDetailsModel personalDetail;
 	private ArrayList<FriendModel> friendList;
 	private ArrayList<PostModel> postList;

}