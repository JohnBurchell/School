package login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author <b>Patrik Backstrom</b>
 * 
 * <br><br>NOTE: THIS IS ONLY IMPLEMENTED BY Patrik.<br>
 * THE BASIC CODE IS FROM A UNKNOWN AUTHOR AND IS ONLY<br>
 * IMPLEMENTED AND MODIFIED BY Patrik.<br>
 */

public class loginDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static Statement stmt = null;

	public static loginBean login(loginBean bean) throws SQLException {
		String username = loginBean.getOwnerUsername();
		String password = bean.getOwnerPassword();
		String searchQuery = "select * from owners where ownerUsername='"
				+ username + "' AND ownerPassword='" + password + "'";

		try {
			currentCon = connectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean userExists = rs.next();

			if (!userExists) {
				bean.setValid(false);
			} else if (userExists) {
				String firstName = rs.getString("ownerFirstName");
				String lastName = rs.getString("ownerLastName");
				String address = rs.getString("ownerAddress");
				String phoneNumber = rs.getString("ownerPhoneNumber");
				String lastActivity = rs.getString("ownerLastActivity");
				int priviliges = rs.getInt("ownerAccountLevel");
				bean.setOwnerFirstName(firstName);
				bean.setOwnerLastName(lastName);
				bean.setOwnerAddress(address);
				bean.setOwnerPhoneNumber(phoneNumber);
				bean.setOwnerAccountLevel(priviliges);
				bean.setOwnerLastActivity(lastActivity);
				bean.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		} finally {
			stmt.close();
			stmt = null;
			currentCon.close();
			currentCon = null;
		}
		return bean;
	}
}
