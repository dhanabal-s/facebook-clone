package connect.db.util;
import java.sql.*;

public class DBConnection {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = "jdbc:mysql://localhost:3306/connection";
            String dbUserName = "root";
            String dbPassword = "root";
            return DriverManager.getConnection(dbUrl,dbUserName,dbPassword); 
		
	}
}