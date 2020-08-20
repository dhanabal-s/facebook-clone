package connect.db.dao;

import java.sql.*;
import java.util.logging.Logger;
import connect.db.util.DBConnection;


public class FriendDao {

	private static Logger log = Logger.getLogger("com.my.connection");

	public static void addFriend(int reqId, int friendId) {

		log.info("<> Inside FriendDao class addFriends Method()");

		try {

			Connection con = DBConnection.getConnection();

			String qry = " INSERT INTO FRIEND_LIST (UID1, UID2) VALUES ( ? , ? ) ";

			PreparedStatement stmt = con.prepareStatement(qry);
			stmt.setInt(1, reqId);
			stmt.setInt(2, friendId);

			stmt.executeUpdate();
		} catch(Exception ex) {

			log.severe("<>"+ex.getMessage());
		}
	}
}