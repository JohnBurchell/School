package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author <b>Patrik Backstrom</b>
 * 
 * <br><br>NOTE: THIS IS ONLY IMPLEMENTED BY Patrik.<br>
 * THE BASIC CODE IS FROM A UNKNOWN AUTHOR AND IS ONLY<br>
 * IMPLEMENTED AND MODIFIED BY Patrik.<br>
 */

public class connectionManager {

	static Connection conn;

	public static Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/";
			String dbName = "YODSSODatabase";
			String uname = "root";
			String pwd = "root";

			Class.forName("com.mysql.jdbc.Driver");
	        
			try {
				conn = DriverManager.getConnection(url + dbName, uname, pwd);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return conn;
	}
}
