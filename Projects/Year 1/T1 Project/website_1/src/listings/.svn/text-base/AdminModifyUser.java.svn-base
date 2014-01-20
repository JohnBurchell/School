package listings;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.connectionManager;

/**
 * @author <b>John</b>
 * <br><br>This servlet deals with modify user data. It, like the other modify servlets<br>
 * gets information from the previous page in the session data.<br>
 */

@WebServlet("/AdminModifyUser")
public class AdminModifyUser extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	PreparedStatement pStatement = null;
	ResultSet rs = null;
	AdminBean adminBean = new AdminBean();
	Collection<AdminBean> myBeans = new ArrayList<AdminBean>();
	String restId;
	String selectedUser;
	
	
	/**
	 * <br><br>The post method here takes the selected users name from the form on the previous web page.<br>
	 * This then passes to another method which populates a user bean to be used for modify user data.<br>
	 * This object is then returned and is added to the session scope, which is then returned to the modify users<br>
	 * page to be modified etc.<br>
	 */
	
@Override
public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	
	
	//Clears object to prevent stacking of users
	myBeans.clear();	
	
	//Gets current name
	selectedUser = request.getParameter("userSelectedName");	
	
	//Calls the method with the required string
	populateUserInfo(selectedUser);	
    
	//Adds the data from populate user into the collection.
    myBeans.add(adminBean);
    
    
    //adds to the session
	request.setAttribute("currentOwner", myBeans);
	//dispatches to another page.
	RequestDispatcher rd = request.getRequestDispatcher("modifyUsers.jsp");
	rd.forward(request, response);
      
	}

/**
 * <br><br>This method populates the user information based on the selected user from the previous page.<br>
 * It takes this string as a requirement to popluate the object.<br>
 * <br>
 * It makes a connection and then queries the server based on who the selected user is.<br>
 * <br>
 * It returns an object populated with the returned query data etc<br>
 */

	public void populateUserInfo(String selectedUser) {		
		

		try {			
		
			conn = connectionManager.getConnection();	
			//SQL Query
			String QueryString = "select * from owners where ownerUserName = ?";
			//prepare the statement
			pStatement = conn.prepareStatement(QueryString);
			//Add variables
			pStatement.setString(1, selectedUser);
			//Execute
			rs = pStatement.executeQuery();		
		
			
			if (rs.next()) {
				
				String ownerFirstName = rs.getString("ownerFirstName");
				String ownerLastName = rs.getString("ownerLastName");
				String ownerUserName = rs.getString("ownerUsername");
				String ownerPassword = rs.getString("ownerPassword");
				String ownerPhoneNumber = rs.getString("ownerPhoneNumber");	
				String ownerAccountLevel = rs.getString("ownerAccountLevel");
				
				adminBean.setOwnerFirstName(ownerFirstName);
				adminBean.setOwnerLastName(ownerLastName);
				adminBean.setOwnerUserName(ownerUserName);
				adminBean.setOwnerPassword(ownerPassword);				
				adminBean.setOwnerPhoneNumber(ownerPhoneNumber);
				adminBean.setOwnerAccountLevel(ownerAccountLevel);
				
			}
			
			conn.close();
			rs.close();
			pStatement.close();				
		
			}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
		@Override
		public void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {			
	
			String buttonPressed = request.getParameter("buttonPressed");			
			
			if (buttonPressed.equals("Delete Owner")) {				
				DeleteRest(request, response);
			}
			else if (buttonPressed.equals("Update Owner")){			
				UpdateRest(request, response);
			}
			
			else {
				System.out.println("Problem with button.");
			}		
			
			
	}
		
	public void DeleteRest(HttpServletRequest request,
			HttpServletResponse response) {
		
		try {			
				
			conn = connectionManager.getConnection();			
				
			String QueryString = "DELETE from OWNERS where ownerUserName = ?";
			//Prepare
			pStatement = conn.prepareStatement(QueryString);
			//Assign
			pStatement.setString(1, selectedUser);
			//Execute
			pStatement.executeUpdate();
				
			System.out.println("Delete completed!");	
			
			//Closes connections etc
			conn.close();
			rs.close();
			pStatement.close();	
			
			//Fowwards to another page.
			RequestDispatcher rd = request.getRequestDispatcher("AdminUserServlet");
			rd.forward(request, response);				
				
			}
			
		catch(Exception e) {
			e.printStackTrace();
			
			}
			
		}
		
		
	/**
	 * <br><br>This updates the database with information taken from the previous page<br>
	 * and upates the owner based on the same information.<br>
	 */
	
	public void UpdateRest(HttpServletRequest request,
			HttpServletResponse response) {   
		
	
		String updateOwnerFirstName = request.getParameter("ownerFirstName");
		String updateOwnerLastName = request.getParameter("ownerLastName");
		String updateOwnerAddress = request.getParameter("ownerAddress");		
		String updateOwnerPhoneNumber = request.getParameter("ownerPhoneNumber");
		String updateOwnerAccountLevel = request.getParameter("ownerAccountLevel");						

		try {			
			
			conn = connectionManager.getConnection();
		
			
			String QueryString = "UPDATE OWNERS set ownerFirstName = ?, ownerLastName = ?, ownerAddress = ?, ownerPhoneNumber = ?, ownerAccountLevel = ? where ownerUserName = ?";
			//Prepare statement
			pStatement = conn.prepareStatement(QueryString);
			//Assign variables
			pStatement.setString(1, updateOwnerFirstName);
			pStatement.setString(2, updateOwnerLastName);
			pStatement.setString(3, updateOwnerAddress);
			pStatement.setString(4, updateOwnerPhoneNumber);
			pStatement.setString(5, updateOwnerAccountLevel);	
			pStatement.setString(6, selectedUser);
			//Execute
			pStatement.executeUpdate();			
			
			System.out.println("Update completed!");
			
			conn.close();
			rs.close();
			pStatement.close();	
			
			RequestDispatcher rd = request.getRequestDispatcher("AdminUserServlet");
			rd.forward(request, response);
			
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}


}
